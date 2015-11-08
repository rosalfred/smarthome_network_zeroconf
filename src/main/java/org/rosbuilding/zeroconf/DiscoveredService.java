/**
 * This file is part of the Alfred package.
 *
 * (c) Mickael Gaillard <mick.gaillard@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.rosbuilding.zeroconf;

import java.util.HashMap;

/**
 *
 * @author Erwan Le Huitouze <erwan.lehuitouze@gmail.com>
 *
 */
public class DiscoveredService extends com.github.rosjava.zeroconf_jmdns_suite.jmdns.DiscoveredService {
    public HashMap<String, String> properties = new HashMap<String, String>();
}
