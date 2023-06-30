export default interface bookingReferenceSource {
  getNewBookingReference(): Promise<string>
}