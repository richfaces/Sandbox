/**
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
package org.richfaces.bootstrap.renderkit;

import java.io.IOException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.richfaces.bootstrap.component.AbstractModal;
import org.richfaces.renderkit.RendererBase;

/**
 * Base class for the modal renderer
 * 
 * @author <a href="http://www.pauldijou.fr">Paul Dijou</a>
 * 
 */
@ResourceDependencies({
        @ResourceDependency(library = "org.richfaces", name = "ajax.reslib"),
        @ResourceDependency(library = "org.richfaces", name = "base-component.reslib"),
        @ResourceDependency(library = "org.richfaces", name = "bootstrap-css.reslib"),
        @ResourceDependency(library = "org.richfaces", name = "bootstrap-js.reslib")})
public abstract class ModalRendererBase extends RendererBase {
    public static final String RENDERER_TYPE = "org.richfaces.bootstrap.ModalRenderer";
    
    public void encodeBeginBody(FacesContext facesContext, UIComponent component) throws IOException {
        AbstractModal modal = (AbstractModal) component;
        
        if(!modal.isCustom()) {
            ResponseWriter responseWriter = facesContext.getResponseWriter(); 
            responseWriter.startElement("div", component);
            responseWriter.writeAttribute("class", "modal-body", null);
        }
    }
    
    public void encodeEndBody(FacesContext facesContext, UIComponent component) throws IOException {
        AbstractModal modal = (AbstractModal) component;
        
        if(!modal.isCustom()) {
            ResponseWriter responseWriter = facesContext.getResponseWriter(); 
            responseWriter.endElement("div");
        }
    }
}
