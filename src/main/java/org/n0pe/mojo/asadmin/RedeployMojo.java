/**
 * asadmin-glassfish-plugin : a maven plugin for glassfish administratives tasks
 * 
 * Copyright (C) 2008  Paul Merlin
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.n0pe.mojo.asadmin;


import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import org.n0pe.asadmin.AsCommandList;
import org.n0pe.asadmin.commands.Deployment;


/**
 * @goal redeploy
 * @description AsAdmin redeploy mojo
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public class RedeployMojo
        extends AbstractAsadminMojo {


    protected AsCommandList getAsCommandList()
            throws MojoExecutionException, MojoFailureException {
        getLog().info("Redeploying application archive: " + appArchive);
        final AsCommandList list = new AsCommandList();
        final Deployment d = new Deployment().archive(appArchive);
        if ("war".equalsIgnoreCase(mavenProject.getPackaging())) {
            d.withContextRoot(contextRoot);
        }
        list.add(d.force(true).deploy());
        return list;
    }


}
