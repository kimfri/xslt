package memoryMapping.modules;

import com.google.inject.AbstractModule;
import memoryMapping.MemoryDbMapping;
import memoryMapping.MemoryDbMappingImpl;

public class MemoryDbModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MemoryDbMapping.class).to(MemoryDbMappingImpl.class);
    }
}
