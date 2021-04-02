# docGenerator

The aim of docGenerator is:

1- Scan modules and generate api documentation like scaladoc do.
For an example, see <https://www.scala-lang.org/api/2.12.3/scala/collection/immutable/List.html>

2- Generate dependencies between modules

For example, if we have:

repo1/
 - module1
 - module2

repo2/
 - module3 (calling module1)

We should see in the documentation generated that module1 and module3 are linked. We should be able to navigate from module3 to module1 to quickly see its api documentation generated during the first step.

3 - Take into account version number
