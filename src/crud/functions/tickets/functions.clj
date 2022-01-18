(ns crud.functions.tickets.functions
  (:use
    [crud.database.tickets.queries],
    [crud.helpers.helpers],
    [crud.functions.login.jwt]
    [crud.functions.email.sendEmail]),
  (:require
    [clojure.data.json :as json],
    [clj-commons.digest :as digest],
    [config.core :refer [env]]))

(defn make-ticket [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [nm (:json-params request)],
        (let [idUser (nm :idUser)],
          (let [idStatus (nm :idStatus)],
            (let [title (nm :title)],
              (let [description (nm :description)],
                (make idUser idStatus title description)
                  (let [slug (get-id-to-slug [request])],
                    (let [idTicket  (get-in (slug 0) [:ticket/idTicket])]
                      (insert-slug idTicket (make-slug title idTicket) )
                      (return 201  (make-json {:msg "Cadastrado com Sucesso!"})))))))))
                      (return 401 (make-json {:msg "Login incorreto, por favor tente novamente!"})))))

(defn update-ticket [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [nm (:json-params request)],
        (let [id (get-in request [:path-params :id])],
          (let [idUser (nm :idUser)],
            (let [idStatus (nm :idStatus)],
              (let [title (nm :title)],
                (let [description (nm :description)],
                  (let [slug (make-slug title id)],
                    (update-by-id id idUser idStatus title description slug)
                    (return 201 (make-json {:msg "Editado com Sucesso!"})))))))))
                    (return 401 (make-json {:msg "Login incorreto, por favor tente novamente!"})))))

(defn  update-status-ticket [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [id (get-in request [:path-params :id])],
        (let [idStatus (get-in request [:path-params :idStatus])],
          (update-status-ticket-id id idStatus)
          (let [idTicket (get-in (read-by-id id) [:ticket/idTicket]),
               status (get-in (read-by-id id) [:status/status]),
               nameTicket (get-in (read-by-id id) [:ticket/title])]
                (sendEmail idTicket nameTicket status )
                (return 200 (make-json {:msg "Status do Ticket Atualizado!"})))))
                (return 401 (make-json {:msg "Login incorreto, por favor tente novamente!"})))))

(defn  read-tickets [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [response (readAll)]
        (return 200 (json/write-str response)))
        (return 401 (make-json {:msg "Login incorreto, por favor tente novamente!"})))))

(defn  read-ticket-by-id [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [id (get-in request [:path-params :id])],
        (let [response  (read-by-id id)]
          (return 200 (json/write-str response))))
          (return 401 (make-json {:msg "Login incorreto, por favor tente novamente!"})))))


(defn  delete-ticket-by-id [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [id (get-in request [:path-params :id])],
        (delete id)
        (return 200 (make-json {:msg "Ticket foi deletado com Sucesso!"})))
        (return 401 (make-json {:msg "Login incorreto, por favor tente novamente!"})))))
