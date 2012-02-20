package org.richfaces.renderkit.html.images;

import org.richfaces.renderkit.html.BaseGradient;
import org.richfaces.resource.DynamicUserResource;

@DynamicUserResource
public class PageHeaderGradient extends BaseGradient {
// --------------------------- CONSTRUCTORS ---------------------------

    public PageHeaderGradient() {
        super(1, 95, 47, "headerBackgroundColor", "headerGradientColor", false);
    }
}
