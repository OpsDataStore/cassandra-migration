package com.contrastsecurity.cassandra.migration.config;

public class Cluster {
    private static final String PROPERTY_PREFIX = "cassandra.migration.cluster.";

    public enum ClusterProperty {
        CONTACTPOINTS(PROPERTY_PREFIX + "contactpoints", "Comma separated values of node IP addresses"),
        PORT(PROPERTY_PREFIX + "port", "CQL native transport port"),
        USERNAME(PROPERTY_PREFIX + "username", "Username for password authenticator"),
        PASSWORD(PROPERTY_PREFIX + "password", "Password for password authenticator"),
        AGREEMENT_TO(PROPERTY_PREFIX + "schemaAgreementTimeout", "Number of seconds to wait for schema agreement before timing out"),
        READ_TO(PROPERTY_PREFIX + "readTimeout", "Number of seconds to wait for read before timing out.");

        private String name;
        private String description;

        ClusterProperty(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }

    private String[] contactpoints = {"localhost"};
    private int port = 9042;
    private String username;
    private String password;
    private int agreementTimeout = 240;

    public Cluster() {
        String contactpointsP = System.getProperty(ClusterProperty.CONTACTPOINTS.getName());
        if (null != contactpointsP && contactpointsP.trim().length() != 0)
            this.contactpoints = contactpointsP.replaceAll("\\s+", "").split("[,]");

        String portP = System.getProperty(ClusterProperty.PORT.getName());
        if (null != portP && portP.trim().length() != 0)
            this.port = Integer.parseInt(portP);

        String usernameP = System.getProperty(ClusterProperty.USERNAME.getName());
        if (null != usernameP && usernameP.trim().length() != 0)
            this.username = usernameP;

        String passwordP = System.getProperty(ClusterProperty.PASSWORD.getName());
        if (null != passwordP && passwordP.trim().length() != 0)
            this.password = passwordP;

        String agreementP = System.getProperty(ClusterProperty.AGREEMENT_TO.getName());
        if (null != agreementP && agreementP.trim().length() != 0)
            this.agreementTimeout = Integer.parseInt(agreementP);
    }

    public String[] getContactpoints() {
        return contactpoints;
    }

    public void setContactpoints(String... contactpoints) {
        this.contactpoints = contactpoints;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAgreementTimeout(int timeout) { this.agreementTimeout = timeout; }

    public int getAgreementTimeout() { return this.agreementTimeout; }
}
