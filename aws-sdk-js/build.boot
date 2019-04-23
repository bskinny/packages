(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[cljsjs/boot-cljsjs "0.10.3" :scope "test"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

;; Example Build and Deploy to Clojars:
;; boot package target
;; boot push --repo clojars --file target/aws-sdk-js-2.394.0-1.jar

(def +lib-version+ "2.394.0")
(def +version+ (str +lib-version+ "-1"))

;; The clojars username and password values will be pulled from .lein/credentials (see .boot/profile.boot)
(set-env! :repositories [["clojars" {:url "https://clojars.org/repo/"}]])

(task-options!
  pom  {:project 'org.clojars.bskinny/aws-sdk-js
		    ;;:project     'cljsjs/aws-sdk-js
        :version     +version+
        :description "AWS Browser SDK"
        :url         "https://github.com/aws/aws-sdk-js"
        :license     {"MIT" "https://opensource.org/licenses/MIT"}
        :scm         {:url "https://github.com/cljsjs/packages"}})

(deftask package []
  (comp
   (download :url (format "https://github.com/aws/aws-sdk-js/archive/v%s.zip" +lib-version+)
             :unzip true)

   (sift :move {#"^aws-sdk-js-.*/dist/aws-sdk.js"  "org.clojars.bskinny/aws-sdk-js/development/aws-sdk-js.inc.js"
                #"^aws-sdk-js-.*/dist/aws-sdk.min.js"  "org.clojars.bskinny/aws-sdk-js/production/aws-sdk-js.min.inc.js"})

   (sift :include #{#"^org.clojars.bskinny"})

   (deps-cljs :name "cljsjs.aws-sdk-js")
   (pom)
   (jar)
	 (validate-checksums)))
