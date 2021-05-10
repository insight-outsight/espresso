package org.ootb.espresso.springcloud.demo.embedded.kafka;

class BootstrapConfiguration {
    private String servers;

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }
}