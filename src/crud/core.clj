(ns crud.core
  (:use crud.routes.routes)
  (:require
    [io.pedestal.http.route :as route],
    [io.pedestal.http.body-params :as body-params],
    [io.pedestal.http :as http]))

(def routes (route/expand-routes general-routes))


(def service-map
  (-> {::http/routes routes
       ::http/port   9999
       ::http/type   :jetty
       ::http/join?  false}
      http/default-interceptors
      (update ::http/interceptors conj (body-params/body-params))))

(http/start (http/create-server service-map))