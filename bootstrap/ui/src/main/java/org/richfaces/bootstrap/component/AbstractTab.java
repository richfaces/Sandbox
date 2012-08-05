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

import org.richfaces.bootstrap.javascript.BootstrapJSPlugin;
import org.richfaces.bootstrap.renderkit.TabRendererBase;
import org.richfaces.cdk.annotations.Attribute;
import org.richfaces.cdk.annotations.JsfComponent;
import org.richfaces.cdk.annotations.JsfRenderer;
import org.richfaces.cdk.annotations.Tag;

/**
 * Base class for the tab component
 *
 * @author <a href="http://community.jboss.org/people/bleathem">Brian Leathem</a>
 */
@BootstrapJSPlugin(name = "tab")
@JsfComponent(
        type = AbstractTab.COMPONENT_TYPE,
        family = AbstractTab.COMPONENT_FAMILY,
        renderer = @JsfRenderer(type = TabRendererBase.RENDERER_TYPE),
        tag = @Tag(name="tab"))
public abstract class AbstractTab extends UIPanel {
    public static final String COMPONENT_FAMILY = "org.richfaces.bootstrap.Tab";
    public static final String COMPONENT_TYPE = "org.richfaces.bootstrap.Tab";
    public static final String DEFAULT_ATTRIBUTE_NAME = "default";

    @Attribute
    public abstract String getTitle();
    
    @Attribute(defaultValue = "false")
    public abstract boolean isDefault();
    
    @Attribute
    public abstract String getName();
}
