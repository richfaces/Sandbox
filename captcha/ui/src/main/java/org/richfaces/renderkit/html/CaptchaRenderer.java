/*
 * JBoss, Home of Professional Open Source
 * Copyright , Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.richfaces.renderkit.html;

import org.richfaces.cdk.annotations.JsfRenderer;
import org.richfaces.component.AbstractCaptcha;
import org.richfaces.renderkit.HtmlConstants;
import org.richfaces.renderkit.RendererBase;

import javax.faces.FacesException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;

@JsfRenderer(family = AbstractCaptcha.COMPONENT_FAMILY, type = CaptchaRenderer.RENDERER_TYPE)
@ResourceDependencies({@ResourceDependency(library = "javax.faces", name = "jsf.js")})
public class CaptchaRenderer extends RendererBase {
// ------------------------------ FIELDS ------------------------------

    public static final String RENDERER_TYPE = "org.richfaces.CaptchaRenderer";


// -------------------------- OTHER METHODS --------------------------

    @Override
    public void doEncodeEnd(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException
    {
        if (getUtils().getNestingForm(context, component) == null) {
            throw new FacesException("Captcha component must be nested within UIForm component");
        }
        if (!(component instanceof AbstractCaptcha)) {
            return;
        }
        AbstractCaptcha captcha = (AbstractCaptcha) component;

        writer.startElement(HtmlConstants.SCRIPT_ELEM, null);
        writer.writeAttribute(HtmlConstants.TYPE_ATTR, "text/javascript", "type");
        writer.write("var RecaptchaOptions = {");
        writer.write("theme : '" + captcha.getTheme() + "'");
        writer.write(",lang : '" + captcha.getLang() + "'");
        writer.write(",tabindex : " + captcha.getTabindex());
        writer.write("};");
        writer.endElement(HtmlConstants.SCRIPT_ELEM);

        writer.startElement(HtmlConstants.SCRIPT_ELEM, null);
        writer.writeAttribute(HtmlConstants.TYPE_ATTR, "text/javascript", "type");
        writer.writeAttribute(HtmlConstants.SRC_ATTRIBUTE, "http://www.google.com/recaptcha/api/challenge?k=" + captcha.getPublicKey() + "&hl=" + captcha.getLang(), "src");
        writer.endElement(HtmlConstants.SCRIPT_ELEM);

        writer.startElement("noscript", null);
        writer.startElement("iframe", null);
        writer.writeAttribute(HtmlConstants.SRC_ATTRIBUTE, "http://www.google.com/recaptcha/api/noscript?k=" + captcha.getPublicKey() + "&hl=" + captcha.getLang(), "src");
        writer.writeAttribute(HtmlConstants.HEIGHT_ATTRIBUTE, "300", "height");
        writer.writeAttribute(HtmlConstants.WIDTH_ATTRIBUTE, "500", "width");
        writer.writeAttribute("frameborder", "0", "frameborder");
        writer.endElement("iframe");

        writer.startElement("textarea", null);
        writer.writeAttribute(HtmlConstants.NAME_ATTRIBUTE, "recaptcha_challenge_field", "name");
        writer.writeAttribute(HtmlConstants.ROWS_ATTRIBUTE, "3", "rows");
        writer.writeAttribute(HtmlConstants.COLS_ATTRIBUTE, "40", "cols");
        writer.endElement("textarea");

        writer.startElement(HtmlConstants.INPUT_ELEM, null);
        writer.writeAttribute(HtmlConstants.TYPE_ATTR, "hidden", "type");
        writer.writeAttribute(HtmlConstants.NAME_ATTRIBUTE, "recaptcha_response_field", "name");
        writer.writeAttribute(HtmlConstants.VALUE_ATTRIBUTE, "manual_challenge", "value");

        writer.endElement("noscript");
    }
}
