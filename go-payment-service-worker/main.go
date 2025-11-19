package main

import (
	"log"
	"payment-service-worker/app"

	"go.temporal.io/sdk/client"
	"go.temporal.io/sdk/worker"
)

func main() {
	c, err := client.Dial(client.Options{})
	if err != nil {
		log.Fatalln("Unable to create client", err)
	}
	defer c.Close()

	w := worker.New(c, "HLC_TRADING_ORDERS_PAYMENT", worker.Options{})

	w.RegisterWorkflow(app.PaymentWorkflow)

	w.RegisterActivity(app.FraudRiskCheck)
	w.RegisterActivity(app.Pay)
	w.RegisterActivity(app.PaymentNotification)

	err = w.Run(worker.InterruptCh())
	if err != nil {
		log.Fatalln("Unable to start worker", err)
	}
}
