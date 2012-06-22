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
package org.richfaces.bootstrap.component;

import javax.faces.component.UIPanel;
import org.richfaces.bootstrap.renderkit.ModalCancelButtonRendererBase;
import org.richfaces.cdk.annotations.Attribute;
import org.richfaces.cdk.annotations.JsfComponent;
import org.richfaces.cdk.annotations.JsfRenderer;
import org.richfaces.cdk.annotations.Tag;
import org.richfaces.component.attribute.CoreProps;

/**
 * Base class for the modalCancelButton component
 * 
 * @author <a href="http://www.pauldijou.fr">Paul Dijou</a>
 * 
 */
@JsfComponent(
        type = AbstractModalCancelButton.COMPONENT_TYPE,
        family = AbstractModalCancelButton.COMPONENT_FAMILY,
        renderer = @JsfRenderer(type = ModalCancelButtonRendererBase.RENDERER_TYPE),
        tag = @Tag(name="modalCancelButton"))
public abstract class AbstractModalCancelButton extends UIPanel implements CoreProps {
    public static final String COMPONENT_FAMILY = "org.richfaces.bootstrap.ModalCancelButton";
    public static final String COMPONENT_TYPE = "org.richfaces.bootstrap.ModalCancelButton";
    
    @Attribute
    public abstract BootstrapSeverity getSeverity();
    
    @Attribute
    public abstract BootstrapSize getScale();
    
    @Attribute
    public abstract String getValue();
}
