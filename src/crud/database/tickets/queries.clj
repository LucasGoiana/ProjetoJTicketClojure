(ns crud.database.tickets.queries
  (:use
    [crud.database.connection],
    [next.jdbc.date-time]),
  (:require
    [next.jdbc.sql :as query],
    [next.jdbc :as jdbc]))

; Inserir Ticket
(defn make [idUser idStatus title description ] (query/insert! ds :ticket
              {:idUser idUser :idStatus idStatus :title title :description description :createdDate (java.util.Date.)}))

;Atualizar Ticket pelo ID
(defn update-by-id [id idUser idStatus title description slug]  (query/update! ds :ticket
             {:idUser idUser :idStatus idStatus :title title :description description :slug slug}{:idTicket id }))

; Pega o ultimo ticket para o slug
(defn get-id-to-slug [request] (read-as-local) (query/query ds ["SELECT idTicket FROM ticket order by idTicket desc limit 1"]))

; Inserir o slug
(defn insert-slug [id slug]  (query/update! ds :ticket
    { :slug slug }{:idTicket id }))

; Buscar Lista de Tickets
(defn readAll [] (read-as-local) (query/query ds ["SELECT t.idTicket, t.title, t.description,  DATE_FORMAT(t.modifiedDate, \"%Y-%m-%d %h:%s:%i\") modifiedDate, u.name user, s.name status FROM  ticket t INNER JOIN user u ON t.idUser = u.idUser INNER JOIN status s ON t.idStatus = s.idStatus "]))

; Buscar um Ticket pelo id
(defn read-by-id [id]  (jdbc/execute-one! ds ["SELECT t.idTicket, t.title, t.description,  DATE_FORMAT(t.modifiedDate, \"%Y-%m-%d %h:%s:%i\") modifiedDate, u.name user, s.name status FROM  ticket t INNER JOIN user u ON t.idUser = u.idUser INNER JOIN status s ON t.idStatus = s.idStatus  WHERE t.idTicket = ?  " id]))

;Deletar Ticket pelo ID
(defn delete [id]  (query/delete! ds :ticket { :idTicket id}))

;Atualizar Ticket pelo ID
(defn update-status-ticket-id [id idStatus]  (query/update! ds :ticket
      {:idStatus idStatus }{:idTicket id }))