package io.kotest.core.spec.style.scopes

import io.kotest.core.test.createTestName

@Suppress("FunctionName")
interface BehaviorSpecScope : RootScope {

   /**
    * Adds a top level [GivenScope] to this spec.
    */
   fun Given(name: String, test: suspend GivenScope.() -> Unit) = addGiven(name, false, test)

   /**
    * Adds a top level [GivenScope] to this spec.
    */
   fun given(name: String, test: suspend GivenScope.() -> Unit) = addGiven(name, false, test)

   /**
    * Adds a top level disabled [GivenScope] to this spec.
    */
   fun xgiven(name: String, test: suspend GivenScope.() -> Unit) = addGiven(name, true, test)

   private fun addGiven(name: String, xdisabled: Boolean, test: suspend GivenScope.() -> Unit) {
      val testName = createTestName("Given: ", name)
      registration().addContainerTest(testName, xdisabled) {
         GivenScope(description().append(testName), lifecycle(), this, defaultConfig()).test()
      }
   }
}