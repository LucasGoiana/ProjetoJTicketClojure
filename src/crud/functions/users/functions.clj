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
               (let [quantityEmail  (get-in  (validate-email email) [:quantityEmail])],
                 (if (>= quantityEmail 1)
                   (return 500 (make-json {:msg "Email já Cadastrado!"}))
                      (let [password (digest/md5 (nm :password)) ],
                        (make idProfile name email password )
                          (let [slug (get-id-to-slug request)],
                            (let [idUsuario  (get-in (slug 0) [:user/idUser])]
                              (insert-slug idUsuario (make-slug name idUsuario ))
                                (return 201  (make-json {:msg "Cadastrado com Sucesso!"})))))))))))
                                (return 401 (make-json {:msg "Login incorreto, por favor tente novamente!"})))))

(defn update-user [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [nm (:json-params request)],
        (let [id (get-in request [:path-params :id])],
          (let [name (nm :name)],
            (let [email (nm :email)],
              (let [quantityEmail  (get-in  (validate-email email) [:quantityEmail])],
                (if (>= quantityEmail 1)
                  (return 500 {:msg "Email já Cadastrado!"})
                    (let [password (digest/md5 (nm :password)) ],
                      (let [slug (make-slug name id)],
                        (update-by-id id name email password slug)
                        (return 201 (make-json {:msg "Editado com Sucesso!"}))))))))))
                        (return 401 (make-json {:msg "Login incorreto, por favor tente novamente!"})))))

(defn read-users [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [response (readAll)]
        (return 200 (json/write-str response)))
        (return 401 (make-json {:msg "Login incorreto, por favor tente novamente!"})))))

(defn read-user-by-id [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [response  (read-by-id (get-in request [:path-params :id]))]
        (return 200 (json/write-str response)))
        (return 401 (make-json {:msg "Login incorreto, por favor tente novamente!"})))))


(defn  delete-user-by-id [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [id (get-in request [:path-params :id])],
          (delete-ticket id)
          (delete id)
          (return 200 (make-json {:msg "Usuário foi deletado com Sucesso!"})))
          (return 401 (make-json {:msg "Login incorreto, por favor tente novamente!"})))))