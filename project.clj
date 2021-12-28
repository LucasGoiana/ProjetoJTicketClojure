(defproject crud "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"],
                 [com.github.seancorfield/next.jdbc "1.2.761"],
                 [mysql/mysql-connector-java "8.0.25"],
                 [yogthos/config "1.1.9"]]
  :profiles {:prod {:resource-paths ["config/prod"]}
             :dev  {:resource-paths ["config/dev"]}}
  :main edn-config-test.core
  :repl-options {:init-ns crud.core})
