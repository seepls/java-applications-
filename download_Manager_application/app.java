importPackage(javax.swing); // adds ability to make GUI e.i buttons etc 
importPackage(javax.swing.border.EmptyBorder);
importPackage(java.awt.event.ActionListener );// awt , api to develop GUI 
importPackage(java.net.URL); // protocol 
importPackage(java.io.FileOutputStream);
importPackage(java.lang.Thread);


var frame = new JFrame("Rhino URL Fetcher ");
var urlfield = new JTextFeild(30);
var button = new JButton("download");
var filechooser = new JFileChooser();
var row = Box.cretaeHorizontalBox();
var col = Box.createVerticalBox();
var padding = new EmptyBorder(3,3,3,3);

// putting them together 

row.add(urlfeild);
row.add(button );
col.add(row);
frame.add(col);
row.setBorder(padding);
frame.pack();
frame.visible = true;

frame.addWindowListener(function(e,name)
{if(name==="windowClosing")
java.lang.System.exit(0);

});


button.addActionListner(function(){
    try{
        var url = new URL (urlfeild.text);
        var response = filechooser.showSaveDialog(frame);
        if(response != JFileChooser.APPROVE_OPTION) return ;
        var file = filechooser.getSelectedFile();
        new java.lang.Thread(function(){download(url,file);}).start();
    }
    catch(e){
        JOptionPane.showMessageDialog(frame , e.message , "Exception",JOptionPane.ERROR_MESSAGE);
    }
});

function download(url, file ){
    try{
        var row = Box.cretaeHorizontalBox ;
        row.setBorder(padding);
        var label  = url.toString()+ ":" ; 
        row.add(new JLabel(label));
        var bar = new JProgressBar(0,100);
        bar.stringPainted = true ; 
        bar.string = file.toString();
        row.add(bar);
        col.add(row);
        frame.pack();
        bar.indeterminate = true ; 
        var conn = url.openConnection();
        conn.connect();
        var len = conn .contentLength;
        if(len){
            bar.maximum  = len ; 
            bar. indeterminate = false ;
        }

        var input  = conn . inputStream ; 
        var output  = new FileOutputStream(file);
        var buffer  = java.lang.reflect.Array.newInstance(java.lang.Byte.TYPE, 4096);
        var num;
        while ((num = input.read(buffer))!= -1){
            output.write(buffer , 0 , num );
            bar.value+= num ;
        }
        output.close ();
        input.close();

 
    }

    catch(e){
        if(bar){
            bar.indeterminate = false ; 
            bar.string = e.toString();
            
        }
    }
}

