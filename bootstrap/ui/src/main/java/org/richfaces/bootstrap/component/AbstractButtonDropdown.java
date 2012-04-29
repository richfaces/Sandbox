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

import org.richfaces.bootstrap.renderkit.ButtonDropdownRendererBase;
import org.richfaces.cdk.annotations.Attribute;
import org.richfaces.cdk.annotations.JsfComponent;
import org.richfaces.cdk.annotations.JsfRenderer;
import org.richfaces.cdk.annotations.Tag;
import org.richfaces.component.Positioning;

import javax.faces.component.UIPanel;

/**
 * Base class for the buttonDropdown component
 *
 * @author <a href="http://community.jboss.org/people/bleathem">Brian Leathem</a>
 */
@JsfComponent(
        type = AbstractButtonDropdown.COMPONENT_TYPE,
        family = AbstractButtonDropdown.COMPONENT_FAMILY,
        renderer = @JsfRenderer(type = ButtonDropdownRendererBase.RENDERER_TYPE),
        tag = @Tag(name = "buttonDropdown"))
abstract public class AbstractButtonDropdown extends UIPanel {
    public static final String COMPONENT_FAMILY = "org.richfaces.bootstrap.ButtonDropdown";
    public static final String COMPONENT_TYPE = "org.richfaces.bootstrap.ButtonDropdown";

    @Attribute
    public abstract String getTitle();

    @Attribute
    abstract public String getButtonStyle();
    
    @Attribute
    abstract public BootstrapSeverity getSeverity();
    
    @Attribute
    abstract public BootstrapSize getScale();
    
    @Attribute
    abstract public Positioning getPosition();
    
    public String getVerticalPositionStyleClass() {
        if(Positioning.topAuto.equals(getPosition())
                || Positioning.topLeft.equals(getPosition())
                || Positioning.topRight.equals(getPosition())) {
            return "dropup";
        } else {
            return "";
        }
    }
    
    public String getHorizontalPositionStyleClass() {
        if(Positioning.autoRight.equals(getPosition())
                || Positioning.bottomRight.equals(getPosition())
                || Positioning.topRight.equals(getPosition())) {
            return "pull-right";
        } else {
            return "";
        }
    }
}
