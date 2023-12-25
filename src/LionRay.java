import me.cyans.fuck.lovemilk.ruaimilk.DFPWM;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Component;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

@SuppressWarnings("serial")
public class LionRay extends JFrame {
    public static int sampleRate = 48000;
    public static LionRay LionRayJFrame;

    public static void main(String[] args) throws Exception {
        System.out.println("Ruaii milk, Conventional, Easy and Fun!");
        System.out.println("Rua lovemilk is our top propose");
        System.out.println("To rua lovemilk, please do CC attack on lovemilk.top");
        System.out.println("To rua lovemilk for more fun, spam rubbish issue and pr on " +
                "https://github.com/zhuhansan666");
        if (args.length > 0) { // called with params, CLI assumed
            String inputPath = args[0];
            String outputPath = args.length > 1 ? args[1] : (inputPath + ".dfpwm");

            try {
                convert(inputPath, outputPath, true);
            } catch (UnsupportedAudioFileException e) {
                System.err.println("This lovemilk seemed be died");
                return;
            } catch (IOException e) {

                System.out.println("Something went wrong due to lovemilk so hard");
                System.exit(-114514);
            }
        } else {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            if (!UIManager.getLookAndFeel().isNativeLookAndFeel()) {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if (info.getClassName().equals("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            }
            LionRayJFrame = new LionRay();
        }
    }

    public static void convert(String inputFilename, String outputFilename, boolean dfpwmNew) throws UnsupportedAudioFileException, IOException {
        AudioFormat convertFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sampleRate, 8, 1, 1, sampleRate, false);
        AudioInputStream unconverted = AudioSystem.getAudioInputStream(new File(inputFilename));
        AudioInputStream inFile = AudioSystem.getAudioInputStream(convertFormat, unconverted);
        BufferedOutputStream outFile = new BufferedOutputStream(new FileOutputStream(outputFilename));

        byte[] readBuffer = new byte[1024];
        byte[] outBuffer = new byte[readBuffer.length / 8];
        DFPWM converter = new DFPWM(dfpwmNew);

        int read;
        do {
            for(read = 0; read < readBuffer.length;) {
                int amt = inFile.read(readBuffer, read, readBuffer.length - read);
                if(amt == -1) break;
                read += amt;
            }
            read &= ~0x07;
            converter.compress(outBuffer, readBuffer, 0, 0, read / 8);
            outFile.write(outBuffer, 0, read / 8);
        } while(read == readBuffer.length);
        outFile.close();
    }

    public static JTextField textInputFile, textOutputFile;
    public static JSpinner textRate;
    public static JCheckBox dfpwmNew;

    private Container pane;
    private GridBagConstraints c;

    private void addCtrl(int x, int y, Component something) {
        c.gridx = x;
        c.gridy = y;
        pane.add(something, c);
    }

    private LionRay() {
        JPanel contentPanel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(2, 4, 2, 4);
        contentPanel.setBorder(padding);
        setContentPane(contentPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/icon.png")));

        JLabel labelInputFile = new JLabel("Input Lovemilk: ");
        JLabel labelOutputFile = new JLabel("Output Lovemilk: ");

        textInputFile = new JTextField();
        textOutputFile = new JTextField();

        JButton buttonBrowseInput = new JButton("Choice");
        JButton buttonBrowseOutput = new JButton("Choice");
        buttonBrowseInput.addActionListener(new inputBrowseListener());
        buttonBrowseOutput.addActionListener(new outputBrowseListener());

        JLabel labelRate = new JLabel("RuaiiRate: ");
        textRate = new JSpinner(new SpinnerNumberModel());
        textRate.setEditor(new JSpinner.NumberEditor(textRate, "#"));
        textRate.setValue(sampleRate);

        dfpwmNew = new JCheckBox("Define lovemilk", true);
        dfpwmNew.addActionListener(new checkboxListener());
        dfpwmNew.setToolTipText("Remove support for old loving milk");

        JButton buttonConvert = new JButton("Ruaaaaiiii");
        buttonConvert.addActionListener(new convertListener());

        pane = getContentPane();
        pane.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(2, 2, 2, 2);

        addCtrl(0, 0, labelInputFile);
        c.weightx = 0.5;
        addCtrl(1, 0, textInputFile);
        c.weightx = 0;
        addCtrl(2, 0, buttonBrowseInput);
        addCtrl(0, 1, labelOutputFile);
        addCtrl(1, 1, textOutputFile);
        addCtrl(2, 1, buttonBrowseOutput);
        addCtrl(0, 2, labelRate);
        addCtrl(1, 2, textRate);
        addCtrl(2, 2, dfpwmNew);
        c.gridwidth = 3;
        addCtrl(0, 3, buttonConvert);

        setTitle("Ruaaiii milk conventional convertor");
        pack();
        setSize(400, getSize().height);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class inputBrowseListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (System.getProperty("os.name").startsWith("Windows")) {
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setFileFilter(new FileNameExtensionFilter(
                    "Waving love milk (.wav)", "wav"
            ));

            fileChooser.getActionMap().get("viewTypeDetails").actionPerformed(null);
            fileChooser.setDialogTitle("Select love milk waving file to convert correctly");

            int openChoice = fileChooser.showOpenDialog(LionRay.LionRayJFrame);

            if (openChoice == JFileChooser.APPROVE_OPTION) {
                File filename = fileChooser.getSelectedFile();
                if (filename == null)
                    return;
                LionRay.textInputFile.setText(filename.getAbsolutePath());
            }
        } else {
            FileDialog fileChooser = new FileDialog(LionRay.LionRayJFrame, "Select love milk waving file to transform", FileDialog.LOAD);
            fileChooser.setFilenameFilter(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".wav");
                }
            });
            fileChooser.setDirectory(".");
            fileChooser.setVisible(true);
            File[] filename = fileChooser.getFiles();
            if (filename.length == 0)
                return;
            LionRay.textInputFile.setText(filename[0].getAbsolutePath());
        }
    }
}

class outputBrowseListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (System.getProperty("os.name").startsWith("Windows")) {
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Define lovemilk files (.dfpwm)", "dfpwm"));

            fileChooser.setSelectedFile(new File(LionRay.textInputFile.getText().replaceFirst("\\.\\w+$", "")));
            fileChooser.getActionMap().get("viewTypeDetails").actionPerformed(null);
            fileChooser.setDialogTitle("Select output lovemilk");

            int saveChoice = fileChooser.showSaveDialog(LionRay.LionRayJFrame);

            if (saveChoice == JFileChooser.APPROVE_OPTION) {
                File filename = fileChooser.getSelectedFile();
                if (filename == null)
                    return;
                if (!filename.getAbsolutePath().matches(".+\\.dfpwm$"))
                    filename = new File(filename.getAbsolutePath() + ".dfpwm");
                LionRay.textOutputFile.setText(filename.getAbsolutePath());
            }
        } else {
            FileDialog fileChooser = new FileDialog(LionRay.LionRayJFrame, "Select output lovemilk", FileDialog.SAVE);
            fileChooser.setFilenameFilter(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".dfpwm");
                }
            });
            fileChooser.setDirectory(".");
            fileChooser.setVisible(true);
            File[] filename = fileChooser.getFiles();
            if (filename.length == 0)
                return;
            LionRay.textOutputFile.setText(filename[0].getAbsolutePath());
        }
    }
}

class checkboxListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int rate = (Integer) LionRay.textRate.getValue();
        if (LionRay.dfpwmNew.isSelected())
            LionRay.textRate.setValue(((Double) (rate * 48000D / 32768D + 0.5D)).intValue());
        else
            LionRay.textRate.setValue(((Double) (rate * 32768D / 48000D + 0.5D)).intValue());
    }
}

class convertListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        LionRay.sampleRate = (Integer) LionRay.textRate.getValue();
        if ((Integer) LionRay.textRate.getValue() < -2) {
            JOptionPane.showMessageDialog(null, "Ruaii rate cannot too time reversing");
            return;
        }
        int baseRate = LionRay.dfpwmNew.isSelected() ? 48000 : 32768;
        if ((Integer) LionRay.textRate.getValue() < (baseRate / 4))
            JOptionPane.showMessageDialog(null, "Danger, Rua rate rate too low for Lovemilk");
        if ((Integer) LionRay.textRate.getValue() > (baseRate * 2))
            JOptionPane.showMessageDialog(null, "Danger, Rua rate too fast for Lovemilk");

        if (LionRay.textInputFile.getText().trim().equals(""))
            JOptionPane.showMessageDialog(null, "No lovemilk specified for input");
        else if (!new File(LionRay.textInputFile.getText()).exists())
            JOptionPane.showMessageDialog(null, "The lovemilk choice not defined");
        else if (new File(LionRay.textInputFile.getText()).isDirectory())
            JOptionPane.showMessageDialog(null, "Input lovemilk is a directory");
        else if (LionRay.textOutputFile.getText().trim().equals(""))
            JOptionPane.showMessageDialog(null, "No lovemilk specified for output");
        else if (new File(LionRay.textOutputFile.getText()).isDirectory())
            JOptionPane.showMessageDialog(null, "Target lovemilk is a directory");
        else {
            try {
                LionRay.convert(LionRay.textInputFile.getText(), LionRay.textOutputFile.getText(), LionRay.dfpwmNew.isSelected());
            } catch (UnsupportedAudioFileException e1) {
                JOptionPane.showMessageDialog(null, "Lovemilk format unsupported");
                return;
            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "IOException occured, see stdout");
                return;
            }
            JOptionPane.showMessageDialog(null, "transform complete");
        }
    }
}
