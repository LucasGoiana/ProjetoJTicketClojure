(ns crud.functions.users.functions
  (:use
    [crud.database.users.queries],
    [crud.helpers.helpers],
    [crud.functions.login.jwt]),
  (:require
    [clojure.data.json :as json],
    [clj-commons.digest :as digest],
    [config.core :refer [env]]))

(defn make-user [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [nm (:json-params request)],
         (let [idProfile (nm :idProfile)],
           (let [name (nm :name)],
             (let [email (nm :email)],
               (let [password (digest/md5 (nm :password)) ],
                 (make idProfile name email password )
                 (let [slug (get-id-to-slug request)],
                   (let [idUsuario  (get-in (slug 0) [:usuario/idUsuario])]
                    (insert-slug idUsuario (make-slug name idUsuario ))
         {:status  201
           :headers headerModified
           :body    (make-json {:msg "Cadastrado com Sucesso!"})})))))))
        {:status  400
         :headers headerModified
         :body    (make-json {:msg "Login incorreto, por favor tente novamente!"})})))

(defn update-user [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [nm (:json-params request)],
        (let [id (get-in request [:path-params :id])],
          (let [name (nm :name)],
            (let [email (nm :email)],
              (let [password (digest/md5 (nm :password)) ],
                (let [slug (make-slug name id)],
                  (update-by-id id name email password slug)
                  {:status  200
                   :headers headerModified
                   :body    (make-json {:msg "Editado com Sucesso!"})}))))))
                  {:status  400
                   :headers headerModified
                   :body    (make-json {:msg "Login incorreto, por favor tente novamente!"})}))
  )

(defn read-users [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [response (readAll request)]
        {:status  200
         :headers headerModified
         :body    (json/write-str response )})
      {:status  400
       :headers headerModified
       :body    (make-json {:msg "Login incorreto, por favor tente novamente!"})})))

(defn read-user-by-id [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [response  (read-by-id (get-in request [:path-params :id]))]
        {:status  200
         :headers headerModified
         :body    (json/write-str response )})
      {:status  400
       :headers headerModified
       :body    (make-json {:msg "Login incorreto, por favor tente novamente!"})})))


(defn  delete-user-by-id [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [id (get-in request [:path-params :id])],
          (delete-ticket id)
          (delete id)
          {:status  200
           :headers headerModified
           :body    (make-json {:msg "Usuário foi deletado com Sucesso!"})})
          {:status  400
           :headers headerModified
           :body    (make-json {:msg "Login incorreto, por favor tente novamente!"})})))