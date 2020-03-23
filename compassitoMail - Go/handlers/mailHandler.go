package handlers

import (
	"bytes"
	"encoding/json"
	"html/template"
	"log"
	"net/http"

	"gopkg.in/gomail.v2"
)

type Info struct {
	Email       string
	Nome        string
	ValorCompra float64
}

func (i Info) Send() {

	t := template.New("template.html")

	var err error
	t, err = t.ParseFiles("template.html")
	if err != nil {
		log.Println(err)
	}

	var tpl bytes.Buffer
	if err := t.Execute(&tpl, i); err != nil {
		log.Print(err)
	}

	result := tpl.String()
	m := gomail.NewMessage()
	m.SetHeader("From", "testada@teste.com")
	m.SetHeader("To", i.Email)
	m.SetHeader("Subject", "teste subj")
	m.SetBody("text/html", result)
	// m.Attach("template.html") enviar um download

	d := gomail.NewDialer("smtp.gmail.com", 587, "joaoterceiro366@gmail.com", "magica123")

	if err := d.DialAndSend(m); err != nil {
		panic(err)
	}

}

func Create(w http.ResponseWriter, r *http.Request) {
	enableCors(&w)
	var i Info

	err := json.NewDecoder(r.Body).Decode(&i)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	i.Send()
}

func enableCors(w *http.ResponseWriter) {
	(*w).Header().Set("Access-Control-Allow-Origin", "*")
}
