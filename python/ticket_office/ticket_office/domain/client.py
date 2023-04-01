from abc import ABCMeta, abstractmethod


class Client(metaclass=ABCMeta):
    @abstractmethod
    def get_train(self, train_id):
        pass

    @abstractmethod
    def get_booking_reference(self):
        pass

    @abstractmethod
    def make_reservation(self, *, train_id, booking_reference, seat_ids):
        pass
