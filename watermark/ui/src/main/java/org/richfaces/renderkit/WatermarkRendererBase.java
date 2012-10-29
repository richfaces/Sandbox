package org.richfaces.renderkit;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.richfaces.component.AbstractWatermark;
import org.richfaces.component.util.InputUtils;

@ResourceDependencies({ @ResourceDependency(name = "jquery.js", target = "head"),
        @ResourceDependency(name = "jquery.watermark.js", target = "head"),
        @ResourceDependency(name = "base-component.reslib", library = "org.richfaces", target = "head"),
        @ResourceDependency(name = "richfaces.watermark.js", target = "head") })
public abstract class WatermarkRendererBase extends InputRendererBase {

    public static final String RENDERER_TYPE = "org.richfaces.WatermarkRenderer";

    protected String getConvertedValue(FacesContext facesContext, AbstractWatermark watermark) {
        final Object value = watermark.getValue();

        Converter converter = InputUtils.findConverter(facesContext, watermark, "value");

        if (converter != null) {
            return converter.getAsString(facesContext, watermark, value);
        } else {
            return value != null ? value.toString() : "";
        }
    }

    protected String getTargetId(FacesContext facesContext, AbstractWatermark watermark) {
        return watermark.getParent().getClientId();
    }
}
