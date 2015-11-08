/**
 * This file is part of the Alfred package.
 *
 * (c) Mickael Gaillard <mick.gaillard@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.rosbuilding.zeroconf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Erwan Le Huitouze <erwan.lehuitouze@gmail.com>
 *
 */
public class NodeConfiguration {
    private static final String MASTER_ADDRESS = "MASTER_ADDRESS";
    private static final String NODE_TYPE = "NODE_TYPE";
    private static final String NODE_PATH = "NODE_PATH";
    private static final String NODE_PERMISSION_NAME = "NODE_PERMISSION_NAME";
    private static final String NODE_PERMISSION_EXCLUDE = "NODE_PERMISSION_EXCLUDE";
    private static final String NODE_CAPABILITY = "NODE_CAPABILITY";

    private String nodeType;
    private String nodePath;
    private String masterAddress;

    private List<NodePermission> permissions =
            new ArrayList<NodeConfiguration.NodePermission>();

    private List<NodeCapability> capabilities =
            new ArrayList<NodeConfiguration.NodeCapability>();

    public NodeConfiguration() {

    }

    public NodeConfiguration(DiscoveredService service) {
        this();

        HashMap<String,String> properties = service.properties;

        if (!properties.isEmpty() && properties.containsKey(NODE_TYPE)) {
            this.masterAddress = properties.get(MASTER_ADDRESS);
            this.nodeType = properties.get(NODE_TYPE);
            this.nodePath = properties.get(NODE_PATH);

            int index = 0;
            while (properties.containsKey(NODE_CAPABILITY + "_" + index)) {
                String value = properties.get(NODE_CAPABILITY + "_" + index);
                this.capabilities.add(NodeCapability.fromValue(Integer.valueOf(value)));
                index++;
            }

            index = 0;
            while (properties.containsKey(NODE_PERMISSION_NAME + "_" + index)) {
                String value = properties.get(NODE_PERMISSION_NAME + "_" + index);
                NodePermission permission = new NodePermission();
                permission.name = value;
                if (properties.containsKey(NODE_PERMISSION_EXCLUDE + "_" + index)) {
                    permission.exclude = Boolean.valueOf(properties.get(NODE_PERMISSION_EXCLUDE + "_" + index));
                }
                this.permissions.add(permission);
                index++;
            }
        }
    }

    public String getMasterAddress() {
        return this.masterAddress;
    }

    public void setMasterAddress(String masterAddress) {
        this.masterAddress = masterAddress;
    }

    public String getNodeType() {
        return this.nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodePath() {
        return this.nodePath;
    }

    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    public List<NodePermission> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(List<NodePermission> permissions) {
        this.permissions = permissions;
    }

    public List<NodeCapability> getCapabilities() {
        return this.capabilities;
    }

    public void setCapabilities(List<NodeCapability> capabilities) {
        this.capabilities = capabilities;
    }

    public DiscoveredService toDiscoveredService() {
        DiscoveredService result = new DiscoveredService();

        HashMap<String, String> map = new HashMap<String, String>();

        map.put(MASTER_ADDRESS, this.masterAddress);
        map.put(NODE_TYPE, this.nodeType);
        map.put(NODE_PATH, this.nodePath);

        for (int i = 0; i < this.permissions.size(); i++) {
            NodePermission permission = this.permissions.get(i);
            map.put(NODE_PERMISSION_NAME + "_" + i, permission.name);
            map.put(NODE_PERMISSION_EXCLUDE + "_" + i, String.valueOf(permission.exclude));
        }

        for (int i = 0; i < this.capabilities.size(); i++) {
            NodeCapability capability = this.capabilities.get(i);
            map.put(NODE_CAPABILITY + "_" + i, String.valueOf(capability.value));
        }

        result.properties = map;

        return result;
    }

    public static class NodePermission {
        private String name;
        private boolean exclude;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isExclude() {
            return exclude;
        }

        public void setExclude(boolean exclude) {
            this.exclude = exclude;
        }
    }

    public static enum NodeCapability {
        ALL(0),
        NODE(1);

        private final int value;

        private NodeCapability(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static NodeCapability fromValue(int value) {
            NodeCapability result = null;

            for (NodeCapability capability : NodeCapability.values()) {
                if (capability.value == value) {
                    result = capability;
                    break;
                }
            }

            return result;
        }
    }
}
