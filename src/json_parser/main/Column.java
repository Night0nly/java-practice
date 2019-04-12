public enum Column {
	ID("ID", 0),
	PUBLISHER("出版社", 3),
	AUTHOR("作者", 5),
	TITLE_ID("タイトルID", 6), 
	TITLE("タイトル名", 7),
	FORMAT("フォーマット",8),
	TYPE("DL区分", 10),
	VOLUME("通番", 11),
	SUBTITILE("配信許諾名", 12),
	PRICE("価格", 16), 
	DL_PERIOD("DL期限",17), 
	DELIVERY_START_DATE("配信開始日", 20),
	DELIVERY_END_DATE("配信終了日",21), 
	PERMISSION_START_DATE("許諾開始日", 18),
	PERMISSION_END_DATE("許諾終了日",19), 
	UPDATE_TIME("更新日時", 23),
	REGISTRATION_TIME("登録日時", 22);

	private final String name;
	private final int position;

	private Column(final String name, final int position) {
		this.name = name;
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public int getPosition() {
		return position;
	}

}
