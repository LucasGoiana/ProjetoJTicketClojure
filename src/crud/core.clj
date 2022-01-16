(ns crud.core
  (:use crud.routes.routes)
  (:require
    [io.pedestal.http.route :as route],
    [io.pedestal.http.body-params :as body-params],
    [io.pedestal.http :as http]))

(def routes (route/expand-routes generalRoutes))

(System/getProperty "org.eclipse.jetty.util.log.class")
(System/getProperty "org.eclipse.jetty.util.log.StdErrLog")
(System/getProperty "org.eclipse.jetty.LEVEL", "OFF")
(def server (atom nil))

(def serviceMap
  (-> {::http/routes routes
       ::http/port   9999
       ::http/type   :jetty
       ::http/join?  false}
      http/default-interceptors
      (update ::http/interceptors conj (body-params/body-params))))


(defn start-server []
  (reset! server  (http/start (http/create-server serviceMap))))

(defn stop-server [] (http/stop @server))

(start-server)
