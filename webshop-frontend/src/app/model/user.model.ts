export class User {
  constructor(
    public personCode: string,
    public firstName: string,
    public lastName: string,
    public email: string,
    public phone: string,
    private _token: string,
    private expirationDate: Date
  ) {}

  get token() {
    if (this.expirationDate == null ||
      this.expirationDate < new Date()) {
        return null;
      }
    return this._token;
  }
}