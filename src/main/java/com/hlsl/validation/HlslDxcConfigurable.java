package com.hlsl.validation;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class HlslDxcConfigurable implements Configurable {

    private TextFieldWithBrowseButton dxcPathField;
    private JTextField defaultProfileField;
    private JTextField defaultEntryPointField;
    private JTextField hlslVersionField;
    private JCheckBox enableValidationCheckBox;
    private JCheckBox treatWarningsAsErrorsCheckBox;

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) String getDisplayName() {
        return "HLSL / DXC";
    }

    @Override
    public @Nullable JComponent createComponent() {
        dxcPathField = new TextFieldWithBrowseButton();
        dxcPathField.addBrowseFolderListener(
                "Select DXC Executable", "Path to dxc or dxc.exe",
                null, FileChooserDescriptorFactory.createSingleFileDescriptor());

        defaultProfileField = new JTextField();
        defaultEntryPointField = new JTextField();
        hlslVersionField = new JTextField();
        enableValidationCheckBox = new JCheckBox("Enable DXC validation");
        treatWarningsAsErrorsCheckBox = new JCheckBox("Treat warnings as errors");

        // Show auto-detected path as hint
        String autoDetected = HlslDxcSettings.autoDetectDxc();
        String hint = autoDetected != null
                ? "Leave empty to use auto-detected: " + autoDetected
                : "DXC not found in PATH, Windows SDK, or Vulkan SDK";

        return FormBuilder.createFormBuilder()
                .addLabeledComponent("DXC executable path:", dxcPathField)
                .addComponentToRightColumn(new JLabel("<html><small>" + hint + "</small></html>"))
                .addLabeledComponent("Default shader profile:", defaultProfileField)
                .addLabeledComponent("Default entry point:", defaultEntryPointField)
                .addLabeledComponent("HLSL version (-HV):", hlslVersionField)
                .addComponent(enableValidationCheckBox)
                .addComponent(treatWarningsAsErrorsCheckBox)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    @Override
    public boolean isModified() {
        HlslDxcSettings settings = HlslDxcSettings.getInstance();
        return !dxcPathField.getText().equals(settings.getDxcPath())
                || !defaultProfileField.getText().equals(settings.getDefaultProfile())
                || !defaultEntryPointField.getText().equals(settings.getDefaultEntryPoint())
                || !hlslVersionField.getText().equals(settings.getHlslVersion())
                || enableValidationCheckBox.isSelected() != settings.isEnableValidation()
                || treatWarningsAsErrorsCheckBox.isSelected() != settings.isTreatWarningsAsErrors();
    }

    @Override
    public void apply() {
        HlslDxcSettings settings = HlslDxcSettings.getInstance();
        settings.setDxcPath(dxcPathField.getText());
        settings.setDefaultProfile(defaultProfileField.getText());
        settings.setDefaultEntryPoint(defaultEntryPointField.getText());
        settings.setHlslVersion(hlslVersionField.getText());
        settings.setEnableValidation(enableValidationCheckBox.isSelected());
        settings.setTreatWarningsAsErrors(treatWarningsAsErrorsCheckBox.isSelected());
    }

    @Override
    public void reset() {
        HlslDxcSettings settings = HlslDxcSettings.getInstance();
        dxcPathField.setText(settings.getDxcPath());
        defaultProfileField.setText(settings.getDefaultProfile());
        defaultEntryPointField.setText(settings.getDefaultEntryPoint());
        hlslVersionField.setText(settings.getHlslVersion());
        enableValidationCheckBox.setSelected(settings.isEnableValidation());
        treatWarningsAsErrorsCheckBox.setSelected(settings.isTreatWarningsAsErrors());
    }
}
