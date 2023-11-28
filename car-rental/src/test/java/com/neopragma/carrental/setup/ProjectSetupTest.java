package com.neopragma.carrental.setup;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Verify project directories and classes are set up as expected.
 */
public class ProjectSetupTest {
    private static final String srcTestResourcesFilename = "file-under-src-test-resources.txt";

    @Test
    public void project_uses_the_desired_release_of_java() {
        // The intent is to check the high-level version number.
        // Maven looks at the Java release. We want release "21.n.n".
        // Gradle looks at the Java version. We want version "11.n.n".
        // We are checking the first two digits plus the dot that follows,
        // with any number of characters after that.
        assertTrue(System.getProperty("java.version")
                .matches("^[1|2]1\\..*"),
                "Actual version is: " + System.getProperty("java.version"));
    }

    @Test
    public void files_can_be_found_under_src_test_resources() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        assertNotNull(classLoader.getResourceAsStream(srcTestResourcesFilename));
    }

}
