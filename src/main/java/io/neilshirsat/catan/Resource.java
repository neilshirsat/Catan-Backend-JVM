package io.neilshirsat.catan;

public enum Resource {

    HILLS(ResourceType.BRICK),
    FOREST(ResourceType.LUMBER),
    MOUNTAINS(ResourceType.ORE),
    FIELDS(ResourceType.WHEAT),
    PASTURE(ResourceType.WOOL),
    DESERT(ResourceType.NONE);

    private ResourceType resourceType;

    Resource(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

}
