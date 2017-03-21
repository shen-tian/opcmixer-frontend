(ns opcmixer-frontend.events
    (:require [re-frame.core :as re-frame]
              [day8.re-frame.http-fx]
              [ajax.core :as ajax]
              [opcmixer-frontend.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 :say-hi
 (fn [db _]
   (assoc db :app-log (rand))))

(re-frame/reg-event-db
 :set-color
 (fn [db [_ hue]]
   (assoc db :hue hue)))

(re-frame/reg-event-db
 :process-blah-response
 (fn [db [_ x]]
   (do 
     (let [pix-1 (->> x
              :1490085852662
              :frame
              (take 3))]
       ;;(prn pix-1)
       (assoc db :pix pix-1)))))

(re-frame/reg-event-fx
 :get-frame
 (fn [{:keys [db]} [_ a]]
   {:http-xhrio {:method :get
                 :uri "http://localhost:8080/"
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success  [:process-blah-response]
                 :on-failure  [:process-blah-response]}
    :db (assoc db :flag true)}))
