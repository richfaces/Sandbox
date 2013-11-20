package org.richfaces.bootstrap.ui.progressbar;

import org.richfaces.bootstrap.component.BootstrapSeverity;
import org.richfaces.bootstrap.ui.pagination.PaginationRendererBase;
import org.richfaces.cdk.annotations.*;
import org.richfaces.ui.attribute.CoreProps;

import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;

/**
 * base class for the ProgressBar component
 *
 * @author Lukas Eichler
 */
@JsfComponent(
        type = AbstractProgressBar.COMPONENT_TYPE,
        family = AbstractProgressBar.COMPONENT_FAMILY,
        renderer = @JsfRenderer(type = ProgressBarRendererBase.RENDERER_TYPE),
        tag = @Tag(name = "progressBar"))
public abstract class AbstractProgressBar extends UIOutput implements CoreProps {

    public static final String COMPONENT_FAMILY = "org.richfaces.bootstrap.ProgressBar";
    public static final String COMPONENT_TYPE = "org.richfaces.bootstrap.ProgressBar";

    @Attribute(description = @Description("Bar Stripped"))
    public abstract boolean isStriped();

    @Attribute(description = @Description("Bar active animated"))
    public abstract boolean isAnimated();

    @Attribute(suggestedValue = BootstrapSeverity.SUCCESS + ","
            + BootstrapSeverity.INFO + ","
            + BootstrapSeverity.WARNING + ","
            + BootstrapSeverity.ERROR, description = @Description("Severity Color of the bar"))
    public abstract String getSeverity();
}
