package cuploader.frames;

import cuploader.Data;
import cuploader.Data.Elem;
import cuploader.PFile;
import cuploader.Settings;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.security.auth.login.CredentialException;
import javax.security.auth.login.CredentialNotFoundException;
import javax.security.auth.login.LoginException;
import javax.swing.*;
import org.wikipedia.Wiki;

public class FUpload extends javax.swing.JFrame {
    ArrayList<PFile> list = new ArrayList<PFile>();
    private volatile boolean stopRq = false;
    
    boolean createGallery;
    int galleryHeader;
    int galleryWidth;
    String galleryPage;
    boolean galleryOnTop;
    
    int fileDescSource;
    String server;
    String author;
    
    String username;
    String source;
    String license;
    String attrib;
    String extratext;
    String categories;
    File f;
    Wiki wiki;
    boolean renameAfterUpload;
    
    public FUpload(ArrayList<PFile> list) {
        this.list = list;
        initComponents();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(FLogin.DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(bHide);  
        bHide.requestFocus(); 
        
        createGallery = Data.settings.createGallery;
        galleryHeader = Data.settings.galleryHeader;
        galleryWidth = Data.settings.galleryWidth;
        fileDescSource = Data.settings.fileDescSource;
        galleryPage = Data.settings.galleryPage;
        galleryOnTop = Data.settings.galleryOnTop;
        
        server = Data.settings.server;
        author = Data.settings.author;
        username = Data.settings.username;
        source = Data.settings.source;
        license = Data.licensesTemplates.get(Data.settings.license);
        
        attrib = Data.settings.attribution;
        extratext = Data.settings.extraText;
        categories = Data.settings.categories;
        
        if(fileDescSource==1 && !Data.settings.fileDescPath.isEmpty())
            f = new File(Data.settings.fileDescPath);
        wiki = Data.wiki;
        renameAfterUpload = Data.settings.renameAfterUpload;
            
        setVisible(true);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);
        startUpload();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Progress = new javax.swing.JProgressBar();
        lName = new javax.swing.JLabel();
        lGallery = new javax.swing.JLabel();
        bCancel = new javax.swing.JButton();
        bHide = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("cuploader/text/messages"); // NOI18N
        setTitle(bundle.getString("upload-uploading")); // NOI18N
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cuploader/resources/-spacer.png"))); // NOI18N
        lName.setText(bundle.getString("upload-uploading") + "...");

        lGallery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cuploader/resources/-spacer.png"))); // NOI18N
        lGallery.setText(bundle.getString("upload-gallery-creating") + "...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Progress, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                    .addComponent(lName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lGallery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lGallery)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        bCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cuploader/resources/cross.png"))); // NOI18N
        bCancel.setText(bundle.getString("button-stop")); // NOI18N
        bCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelActionPerformed(evt);
            }
        });

        bHide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cuploader/resources/arrow-circle-135-left.png"))); // NOI18N
        bHide.setText(bundle.getString("button-hide")); // NOI18N
        bHide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHideActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bHide)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCancel)
                    .addComponent(bHide))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bHideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHideActionPerformed
        setVisible(false);
    }//GEN-LAST:event_bHideActionPerformed

    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelActionPerformed
        bCancel.setEnabled(false);
        stopUpload();
    }//GEN-LAST:event_bCancelActionPerformed
    
    private void startUpload() {
    stopRq = false;
    lockLogout(true);

    Runnable run = new Runnable() {
        @Override
        public void run() {
            Progress.setMinimum(0);
            Progress.setMaximum(Data.filesUpload);
            
            String gallery="",header="";
            int toUpload = list.size();
            int uploaded = 0;
            
            //READ DESC FROM FILE
            BufferedReader in = null;
            String text = "";
            if(fileDescSource==1 && f!= null) {
                try {
                    in = new BufferedReader(new FileReader(f));
                    
                    String string;
                    while ((string = in.readLine()) != null)
                        text += string + "\n";
                    
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Error: " + ex.toString());
                    stopUpload();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Error: " + ex.toString());
                    stopUpload();
                }
            }
            
            //GALLERY HEADER
            if(createGallery && !stopRq) {
                if(galleryHeader==1)
                    header = JOptionPane.showInputDialog(rootPane, Data.text("upload-gallery-name"), Data.text("upload-uploading"), JOptionPane.INFORMATION_MESSAGE);
                else if(galleryHeader==0){
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Calendar cal = Calendar.getInstance();
                    header = df.format(cal.getTime());
                } else
                    header = "?";
                //gallery += "==" + header + "==\n<gallery>\n";
                gallery += "<gallery widths=" + galleryWidth + ">\n";
            } else
                lGallery.setEnabled(false);
            
            //LOCK PANELS TO UPLOAD
            for(PFile file : Data.getFiles())
                if(file.toUpload)
                    file.lockPanel(true);
            
            int i=0;
            for(PFile file : list) {
                String desc = "";
                
                if(!stopRq) {
                    
                    //DEFAULT DESC
                    if(fileDescSource==0) {
                        
                        //COMMONS
                        if(server.equals("commons.wikimedia.org")) {
                            desc = "=={{int:filedesc}}==\n{{Information"
                            + "\n|description = " + file.getComponent(Elem.DESC)
                            + "\n|date = " + file.getComponent(Elem.DATE);
                            
                            if(author.equals("own")) desc += "\n|source = {{own}}\n|author = [[user:" + username + "|]]";
                            else desc += "\n|source = " + source + "\n|author = " + author;
                            
                            desc += "\n|permission = \n|other_versions = \n}}";
                            if(file.coor != null)
                                desc += "\n{{Location dec|" + file.coor.getLat() + "|" + file.coor.getLon() + "}}";

                            desc += "\n\n=={{int:license-header}}==\n";
                            
                            if(attrib.isEmpty()) license.replace("|%ATTRIB%", "%ATTRIB%");  //delete pipe
                            desc += license.replace("%ATTRIB%", attrib);

                            if(!extratext.isEmpty())
                                desc += "\n" + extratext;

                        //EVERYTHING ELSE
                        } else {
                            desc = "==Summary=="
                            + "\n* Description: " + file.getComponent(Elem.DESC);
                            
                            if(author.equals("own")) desc += "\n* Source: own work\n* Author: [[user:" + username + "|]]";
                            else desc += "\n* Source: " + source + "\n* Author: " + author;
                            
                            desc += "\n* Date: " + file.getComponent(Elem.DATE)
                            + "\n* License: " + license.replace("%ATTRIB%", attrib);
                            if(!extratext.isEmpty()) 
                                desc += "\n" + extratext;
                        }

                        //CATEGORIES (FOR BOTH)
                        String c = "";
                        if(categories.matches(".*\\w.*"))
                            c += categories + ";";
                        if(file.getComponent(Elem.CATS).matches(".*\\w.*"))
                            c += file.getComponent(Elem.CATS);

                        desc += "\n\n";
                        if(c.matches(".*\\w.*")) {
                            //String categories = Data.settings.categories + ";" + file.getComponent(Elem.CATS);

                            String[] c2 = c.split(";");
                            boolean b;
                            for(int j=0;j<c2.length;++j) {
                                b = true;
                                for(int k=0; k<j; ++k) {
                                    if(c2[j].equals(c2[k])) {
                                        b = false;
                                        break;
                                    }
                                }
                                if(b && !c2[j].equals("") && !c2[j].matches(".*"))
                                    desc += "[[Category:" + c2[j] + "]]\n";
                            }
                        }
                        desc += "[[Category:Uploaded with VicuñaUploader]]";
                    }

                    // DESC FROM EXTERNAL FILE
                    if(fileDescSource==1 && f!= null) {          
                        desc = text;

                        desc = (attrib.isEmpty()) ? desc.replace("%ATTRIB%", "") : desc.replace("%ATTRIB%", attrib);
                        desc = desc.replace("%AUTHOR%", author);

                        //CATEGORIES (FOR ALL)
                        String out = "";
                        if(!categories.isEmpty()) {
                            String[] c2 = categories.split(";");

                            boolean b;
                            for(int j=0;j<c2.length;++j) {
                                b = true;
                                for(int k=0; k<j; ++k) {
                                    if(c2[j].equals(c2[k])) {
                                        b = false;
                                        break;
                                    }
                                }
                                if(b && !c2[j].equals(""))
                                    out += "[[Category:" + c2[j] + "]]\n";
                            }
                        }
                        desc = desc.replace("%CATEGORIES%", out);
                        
                        desc = (!extratext.isEmpty()) ? desc.replace("%EXTRA%", "") : desc.replace("%EXTRA%", extratext);
                        desc = desc.replace("%SOURCE%", source);
                        desc = desc.replace("%USER%", username);

                        //CATEGORIES (FOR IMAGE)
                        boolean b;
                        String[] c2 = file.getComponent(Elem.CATS).split(";");
                        out = "";
                        for(int j=0;j<c2.length;++j) {
                            b = true;
                            for(int k=0; k<j; ++k) {
                                if(c2[j].equals(c2[k])) {
                                    b = false;
                                    break;
                                }
                            }
                            if(b && !c2[j].equals(""))
                                out += "[[Category:" + c2[j] + "]]\n";
                        }
                        desc = desc.replace("%CATS%", out);

                        //COORDINATES
                        c2 = file.getComponent(Elem.COOR).split(";");
                        out = "";
                        if(c2.length>1)
                            out += "{{Location dec|" + c2[0] + "|" + c2[1] + "}}";
                        desc = desc.replace("%COOR%", out);

                        desc = desc.replace("%DATE%", file.getComponent(Elem.DATE));
                        desc = desc.replace("%DESC%", file.getComponent(Elem.DESC));
                    }
                    if(fileDescSource==1 && f == null) {
                        JOptionPane.showMessageDialog(rootPane, "Error: problem with description file!");
                        break;
                    }

                    String name = file.getComponent(Elem.NAME);

                    //FIXES
                    if(name.endsWith(" "))
                        name = name.substring(0, name.length()-1);
                    name = name.replace("  ", " ");
                    name += "." + file.getComponent(Elem.EXT);

                    lName.setText(Data.text("upload-uploading") + " " + (int)(i+1) + " / " + toUpload + ": " + name + "...");
                    lName.setIcon(new ImageIcon(getClass().getResource("/cuploader/resources/ui-progress-bar-indeterminate.gif")));

                    //upload test
                    //System.out.println(desc);
                    //try { Thread.sleep(2000); } catch (InterruptedException ex) {}

                    try { 
                        boolean fileExist = wiki.isPageExist(name);
                        if(!fileExist) wiki.upload(file.file, name, desc, "VicuñaUploader " + Data.version);
                        
                        if(createGallery) gallery += "File:" + name + "|" + file.getComponent(Elem.DESC).replaceAll("\n", "") + "\n";
                        if(renameAfterUpload) {
                            File f = new File(file.file.getParentFile()+"\\"+name);
                            file.file.renameTo(f);
                            file.file = f;
                        }
                        file.setAsUploaded();
                        ++uploaded;
                    } catch (UnknownError ex) {
                        file.setAsFailed(Data.text("upload-error-file") + ": " + ex.getLocalizedMessage());
                    } catch (CredentialNotFoundException ex) {
                        JOptionPane.showMessageDialog(rootPane, Data.text("upload-error-account"), Data.text("upload-uploading"), JOptionPane.ERROR_MESSAGE);
                        break;
                    } catch (CredentialException ex) {
                        file.setAsFailed(Data.text("upload-error-file-protected"));
                    } catch (LoginException ex) {
                        file.setAsFailed(ex.getLocalizedMessage());
                    } catch (IOException ex) {
                        file.setAsFailed(ex.getLocalizedMessage());
                    }
                    Progress.setValue(i+1);
                    ++i;
                }
            }
                
            lName.setText(Data.text("upload-file-uploaded") + ": " + uploaded + " / " + + toUpload);
            if(toUpload==uploaded)
                lName.setIcon(new ImageIcon(getClass().getResource("/cuploader/resources/tick.png")));
            else
                lName.setIcon(new ImageIcon(getClass().getResource("/cuploader/resources/exclamation.png")));

            if(createGallery) gallery += "</gallery>";
            if(createGallery && uploaded>0) {
                Progress.setIndeterminate(true);
                lGallery.setIcon(new ImageIcon(getClass().getResource("/cuploader/resources/ui-progress-bar-indeterminate.gif")));
                if(header==null)
                    header = "?";
                try {
                    if(galleryOnTop) {
                        String pageText = wiki.getPageText("User:"+username+"/"+galleryPage);
                        String output = "== " + header + " ==\n\n" + gallery + "\n\n" + pageText;
                        wiki.edit("User:"+username+"/"+galleryPage, output, header);
                    } else
                        wiki.edit("User:"+username+"/"+galleryPage, gallery, header, -1);

                    lGallery.setIcon(new ImageIcon(getClass().getResource("/cuploader/resources/tick.png")));
                    lGallery.setText(Data.text("upload-gallery-created"));
                } catch (CredentialException ex) {
                    lGallery.setIcon(new ImageIcon(getClass().getResource("/cuploader/resources/exclamation.png")));
                    lGallery.setText(Data.text("upload-gallery-creatingerror") + ": " + ex.getLocalizedMessage());
                } catch (LoginException ex) { 
                } catch (IOException ex) {
                    lGallery.setIcon(new ImageIcon(getClass().getResource("/cuploader/resources/exclamation.png")));
                    lGallery.setText(Data.text("upload-gallery-creatingerror") + ": " + ex.getLocalizedMessage());
                } 
            }
            stopUpload();
            Progress.setIndeterminate(false);
            Progress.setValue(Progress.getMaximum());
            bHide.setIcon(new ImageIcon(getClass().getResource("/cuploader/resources/tick.png")));
            bHide.setText(Data.text("button-close"));
        }
    };
    
    Thread t = new Thread(run);
    t.start();
}

    private void stopUpload() {
        stopRq = true;
        for(PFile file : Data.getFiles())
            if(!file.editable)
                file.lockPanel(false);
        Main.bLogin.setEnabled(true);
        //setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar Progress;
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bHide;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lGallery;
    private javax.swing.JLabel lName;
    // End of variables declaration//GEN-END:variables

    KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
    Action escapeAction = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            stopUpload();
        }
    };

    private void lockLogout(boolean b) {
        Main.bLogin.setEnabled(!b);
        Main.mLogin.setEnabled(!b);
    }
}
