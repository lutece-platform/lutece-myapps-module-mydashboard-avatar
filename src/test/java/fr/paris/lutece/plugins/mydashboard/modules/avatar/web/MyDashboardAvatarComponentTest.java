/*
 * Copyright (c) 2002-2014, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.mydashboard.modules.avatar.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.Test;

import fr.paris.lutece.plugins.mydashboard.service.IMyDashboardComponent;
import fr.paris.lutece.test.LuteceTestCase;
import fr.paris.lutece.test.mocks.MockHttpServletRequest;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

/**
 * Tests the avatar dashboard component
 */
public class MyDashboardAvatarComponentTest extends LuteceTestCase
{
    private static final String COMPONENT_ID = "mydashboard.myDashboardAvatarComponent";

    @Inject
    private Instance<IMyDashboardComponent> _components;

    @Inject
    private MyDashboardAvatarComponent _component;

    /**
     * The component is discovered by CDI as a dashboard component
     */
    @Test
    public void testComponentIsDiscovered( )
    {
        assertTrue( _components.stream( ).anyMatch( c -> COMPONENT_ID.equals( c.getComponentId( ) ) ), "the avatar component should be a CDI dashboard component" );
    }

    /**
     * The description is read from the module bundle
     */
    @Test
    public void testComponentDescription( )
    {
        assertEquals( "My Avatar", _component.getComponentDescription( Locale.ENGLISH ), "the description should come from the module bundle" );
        assertEquals( "Mon avatar", _component.getComponentDescription( Locale.FRENCH ), "the description should come from the french bundle" );
    }

    /**
     * Without a registered user the component renders nothing
     */
    @Test
    public void testNoDataWithoutUser( )
    {
        assertEquals( "", _component.getDashboardData( new MockHttpServletRequest( ) ), "no user, no avatar block" );
    }
}
