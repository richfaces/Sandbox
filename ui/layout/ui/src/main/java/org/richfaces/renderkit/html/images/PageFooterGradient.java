package org.richfaces.renderkit.html.images;

import org.richfaces.renderkit.html.BaseGradient;
import org.richfaces.resource.DynamicUserResource;

@DynamicUserResource
public class PageFooterGradient extends BaseGradient {
// --------------------------- CONSTRUCTORS ---------------------------

    public PageFooterGradient() {
        super(1, 100, 50, "panelBorderColor", "generalBackgroundColor", false);
    }
}
