package memoryMapping;

import com.google.inject.Guice;
import com.google.inject.Injector;
import memoryMapping.modules.MemoryDbModule;

import java.util.Arrays;
import java.util.List;

public class StarterKit {
    public static void main(String[] args) {
        StarterKit starterKit = new StarterKit();
        starterKit.doit();
    }

    private void doit() {
         Injector injector = Guice.createInjector(new MemoryDbModule());
         DbClient dbClient = injector.getInstance(DbClient.class);

        List<String> ids = Arrays.asList("1", "1", "2", "3", "2", "1", "1", "1");
        ids.forEach(dbClient::doSomeMapping);

    }
}
