package Main;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import Data.BoardTest;
import Processor.CheckerTest;
import Processor.SolverTest;

@RunWith(JUnitPlatform.class) //TODO does not work
@SelectClasses({
    BoardTest.class,
    CheckerTest.class,
    SolverTest.class
})
public class AllTests {

}
