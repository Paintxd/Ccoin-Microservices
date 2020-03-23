package main

import (
	"log"
	"net/http"

	"github.com/Paintxd/compassitoMail/handlers"
)

func main() {
	mux := http.NewServeMux()
	mux.HandleFunc("/mail", handlers.Create)

	err := http.ListenAndServe(":8000", mux)
	log.Fatal(err)
}
