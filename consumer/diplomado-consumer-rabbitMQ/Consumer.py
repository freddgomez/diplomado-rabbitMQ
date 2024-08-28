import pika


def main():
    connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
    channel = connection.channel()

    channel.queue_declare(queue='notification')

    def callback(ch, method, properties, body):
        print(f" [x] Recibido: {body}")

    channel.basic_consume(queue='notification', on_message_callback=callback, auto_ack=True)

    print(' [*] Esperando mensajes. para salir presione CTRL+C')
    channel.start_consuming()


if __name__ == '__main__':
    main()
