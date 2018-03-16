/*
 * ProActive Parallel Suite(TM):
 * The Open Source library for parallel and distributed
 * Workflows & Scheduling, Orchestration, Cloud Automation
 * and Big Data Analysis on Enterprise Grids & Clouds.
 *
 * Copyright (c) 2007 - 2017 ActiveEon
 * Contact: contact@activeeon.com
 *
 * This library is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation: version 3 of
 * the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * If needed, contact us to obtain a release under GPL Version 2 or 3
 * or a different license than the AGPL.
 */
package jsr223.perl.bindings;

import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.junit.Test;


public class PerlStringBindingsAdderTest {

    @Test
    public void testAddStringVariables() throws ScriptException, IOException {
        PerlStringBindingsAdder perlStringBindingsAdder = new PerlStringBindingsAdder();

        Bindings bindings = new SimpleBindings();
        Map<String, String> variables = new HashMap<>();
        bindings.put("name", "EchoUbuntu");
        bindings.put("key", "value");
        bindings.put("greetings", "Hello World");

        perlStringBindingsAdder.addBindingToStringMap(bindings, variables);

        for (Map.Entry<String, String> expectedEntry : variables.entrySet()) {
            assertThat(variables, hasEntry(expectedEntry.getKey(), expectedEntry.getValue()));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testKeyIsNull() throws ScriptException, IOException {
        PerlStringBindingsAdder perlStringBindingsAdder = new PerlStringBindingsAdder();

        Bindings bindings = new SimpleBindings();
        Map<String, String> variables = new HashMap<>();
        bindings.put("name", "EchoUbuntu");
        bindings.put(null, "value");

        perlStringBindingsAdder.addBindingToStringMap(bindings, variables);
    }

    @Test
    public void testValueIsNull() throws ScriptException, IOException {
        PerlStringBindingsAdder perlStringBindingsAdder = new PerlStringBindingsAdder();

        Bindings bindings = new SimpleBindings();
        Map<String, String> variables = new HashMap<>();
        bindings.put("name", "EchoUbuntu");
        bindings.put("key", null);

        perlStringBindingsAdder.addBindingToStringMap(bindings, variables);

        assertThat(variables, hasEntry("name", "EchoUbuntu"));
        assertThat(variables.containsKey("value"), is(false));
    }

    @Test
    public void testBindingsIsNull() throws ScriptException, IOException {
        PerlStringBindingsAdder perlStringBindingsAdder = new PerlStringBindingsAdder();

        Map<String, String> variables = new HashMap<>();

        perlStringBindingsAdder.addBindingToStringMap(null, variables);

        assertThat(variables.size(), is(0));
    }

    @Test
    public void testVariablesIsNull() throws ScriptException, IOException {
        PerlStringBindingsAdder perlStringBindingsAdder = new PerlStringBindingsAdder();

        Bindings bindings = new SimpleBindings();
        bindings.put("name", "EchoUbuntu");
        bindings.put("key", "value");

        perlStringBindingsAdder.addBindingToStringMap(bindings, null);

        assertThat(bindings.size(), is(2));
    }

    @Test
    public void testVariablesAndBindingsIsNull() throws ScriptException, IOException {
        PerlStringBindingsAdder perlStringBindingsAdder = new PerlStringBindingsAdder();

        perlStringBindingsAdder.addBindingToStringMap(null, null);
    }
}
