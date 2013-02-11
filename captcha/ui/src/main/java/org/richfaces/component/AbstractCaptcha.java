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
package org.richfaces.component;

import org.richfaces.cdk.annotations.Attribute;
import org.richfaces.cdk.annotations.Description;
import org.richfaces.cdk.annotations.JsfComponent;
import org.richfaces.cdk.annotations.JsfRenderer;
import org.richfaces.cdk.annotations.Tag;
import org.richfaces.cdk.annotations.TagType;
import org.richfaces.log.LogFactory;
import org.richfaces.log.Logger;
import org.richfaces.renderkit.html.CaptchaRenderer;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@JsfComponent(tag = @Tag(name = "captcha", type = TagType.Facelets),
    renderer = @JsfRenderer(family = AbstractCaptcha.COMPONENT_FAMILY, type = CaptchaRenderer.RENDERER_TYPE))
public abstract class AbstractCaptcha extends UIInput {
// ------------------------------ FIELDS ------------------------------

    public static final String CAPTCHA_BUNDLE = "org.richfaces.component.UICaptcha";

    public static final String COMPONENT_FAMILY = "org.richfaces.Captcha";

    public static final String COMPONENT_TYPE = "org.richfaces.Captcha";

    public static final String INVALID_CAPTCHA_MESSAGE = "org.richfaces.component.UICaptcha.invalid";

// -------------------------- OTHER METHODS --------------------------

    private static final Logger LOG = LogFactory.getLogger(AbstractCaptcha.class);

    private void callRecaptcha(String recaptcha_challenge_fields, String recaptcha_response_fields, String remoteip, URLConnection connection)
        throws IOException
    {
        OutputStream outputStream = connection.getOutputStream();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("privatekey=").append(getPrivateKey());
        stringBuilder.append("&remoteip=").append(remoteip);
        stringBuilder.append("&challenge=").append(recaptcha_challenge_fields);
        stringBuilder.append("&response=").append(recaptcha_response_fields);
        outputStream.write(stringBuilder.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }

    @Attribute(suggestedValue = "lang", description = @Description("Possible values: en, nl, fr, de, pt, ru, es, tr"))
    public abstract String getLang();

    @Attribute(required = true)
    public abstract String getPrivateKey();

    @Attribute(required = true)
    public abstract String getPublicKey();

    @Attribute(suggestedValue = "tabindex", defaultValue = "0")
    public abstract Integer getTabindex();

    @Attribute(suggestedValue = "theme", description = @Description("Theme of captcha. Possible values: red, white, blackglass, clean"))
    public abstract String getTheme();

    private Boolean isRecaptchaValid(URLConnection connection) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String result = bufferedReader.readLine();
        bufferedReader.close();
        return Boolean.valueOf(result);
    }

    private void setDefaultMessage(FacesMessage message)
    {
        try {
            message.setSummary(ResourceBundle.getBundle(CAPTCHA_BUNDLE).getString(INVALID_CAPTCHA_MESSAGE));
        } catch (MissingResourceException e1) {
            if (LOG.isWarnEnabled()) {
                LOG.warn("Cannot find resource " + e1.getKey() + " in bundle " + e1.getClassName());
            }
            message.setSummary(CAPTCHA_BUNDLE);
        }
    }

    private URLConnection setupConnection() throws IOException
    {
        URL url = new URL("http://www.google.com/recaptcha/api/verify");
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        return connection;
    }

    @Override
    public void validate(FacesContext context)
    {
        if (context == null) {
            throw new NullPointerException();
        }

        String recaptcha_challenge_fields = context.getExternalContext().getRequestParameterMap().get("recaptcha_challenge_field");
        String recaptcha_response_fields = context.getExternalContext().getRequestParameterMap().get("recaptcha_response_field");
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String remoteip = request.getRemoteAddr();

        try {
            URLConnection connection = setupConnection();
            callRecaptcha(recaptcha_challenge_fields, recaptcha_response_fields, remoteip, connection);
            setValid(isRecaptchaValid(connection));

            if (!isValid()) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                try {
                    ResourceBundle applicationBundle = ResourceBundle.getBundle(context.getApplication().getMessageBundle());
                    message.setSummary(applicationBundle.getString(INVALID_CAPTCHA_MESSAGE));
                } catch (MissingResourceException e) {
                    setDefaultMessage(message);
                } catch (NullPointerException e) {
                    setDefaultMessage(message);
                }
                context.addMessage(getClientId(), message);
            }
        } catch (Exception e) {
            throw new FacesException(e);
        }
    }
}
