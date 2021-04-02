# docGenerator

This project is in progress.

##0- To do list

- Look at the SemanticDb project

- As regard the Scalameta project:
 - see how to manage import
 - use sbt dialect to read sbt files
 - When the return value is not set, scalameta don't know it. Check how scala compiler infer this.

- Take into account version number

##Aim of docGenerator

###Scan modules and generate api documentation like scaladoc do.

Iterate over folders recursively and extract build.sbt files.

Generate documentation per module

###Manage dependencies between modules

For example, if we have:

repo1/
 - module1
 - module2

repo2/
 - module3 (calling module1)

We should see in the documentation generated that module1 and module3 are linked. 
Then generate an html file from which we should be able to navigate from module3 to module1 to quickly browse the structure of the project.

