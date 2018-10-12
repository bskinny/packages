(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[cljsjs/boot-cljsjs "0.9.0" :scope "test"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "1.1.4")
(def +version+ (str +lib-version+ "-0"))

(task-options!
  pom  {:project     'cljsjs/amazon-cognito-auth
        :version     +version+
        :description "Amazon Cognito Auth"
        :url         "https://github.com/aws/amazon-cognito-auth-js"
        :license     {"Apache-2.0" "http://aws.amazon.com/apache2.0/"}
        :scm         {:url "https://github.com/aws/amazon-cognito-auth-js"}})

(deftask package []
  (comp
   (download  :url      (str "https://rawgit.com/aws/amazon-cognito-auth-js/master/dist/amazon-cognito-auth.js"))
   (download  :url      (str "https://rawgit.com/aws/amazon-cognito-auth-js/master/dist/amazon-cognito-auth.min.js"))
   (sift      :move     {#"^amazon-cognito-auth.js"
                         "cljsjs/amazon-cognito-auth/development/amazon-cognito-auth.inc.js"
                         #"^amazon-cognito-auth.min.js"
                         "cljsjs/amazon-cognito-auth/production/amazon-cognito-auth.min.inc.js"})
   (sift      :include  #{#"^cljsjs"})
   (deps-cljs :name     "cljsjs.amazon-cognito-auth")
   (pom)
   (jar)))
