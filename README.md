# RecyclerView agregando headers
Agregando header a un recyclerview 

##Ejemplo

Para generar un header dentro de nuestro recycler debemos primero generar un arreglo el cual se encargara de llenar nuestra lista:
```java
private static final String[] lstStringsNumber = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
```
A continuacion generamos nuestros layouts:
###ActivityMain.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
xmlns:tools="http://schemas.android.com/tools"
android:orientation="vertical">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">
        <android.support.v7.widget.RecyclerView
            android:id="@+id/my_list"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            tools:listitem="@layout/item_list"
            android:background="#f2f2f2"
            />
    </LinearLayout>
</LinearLayout>
```
###Itemlist.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="10dp"
    android:layout_marginBottom="1dp">

    <!-- A CardView that contains a TextView -->
    <android.support.v7.widget.CardView
        xmlns:card_view="http://schemas.android.com/apk/res-auto"
        android:id="@+id/card_view"
        android:layout_gravity="center"
        android:layout_width="match_parent"
        android:layout_height="140dp"
        card_view:cardElevation="2dp"
        card_view:contentPadding="10dp"
        card_view:cardCornerRadius="4dp">

        <TextView
            android:id="@+id/txt_content"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:textSize="20dp"
            android:text="ABC"/>

    </android.support.v7.widget.CardView>
</LinearLayout>
```
###ItemHeader.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginBottom="10dp">

    <ImageView
        android:layout_width="wrap_content"
        android:layout_height="120dp"
        android:scaleType="fitXY"
        android:src="@drawable/bg_home"/>
</LinearLayout>
```

A continuacion generamos nuestro Adapter, el cual nos va a permitir saber el tipo de contenido que vamos a manejar dentro de la lista,
en este caso seran dos tipos:
* HEADER
* ITEM
El adaptador sera el encargado de reconocer que tipo de item vamos a pintar dentro de el esto por medio del Metodo @Override:
```java
@Override
public int getItemViewType(int position) {
    return position == 0 ? HEADER : ITEM;
}
```
Dentro de nuestro adaptador agregamos nuestras dos clases extendidas de ViewHolder las cuales se encargaran de buscar nuestros 
elementos dentro de la vista:
###Item
```java
public static class itemViewHolder extends RecyclerView.ViewHolder{
    public TextView textView;

    public itemViewHolder(View itemView) {
        super(itemView);

        this.textView = (TextView) itemView.findViewById(R.id.txt_content);
    }
}
```
###Header
```java
public static class headerViewHolder extends RecyclerView.ViewHolder{
  public headerViewHolder(View itemView) {
      super(itemView);
  }
}
```
Una vez creado nuestro header sigue instanciar que tipo de vista vamos a inflar dentro de nuestro View:
```java
@Override
public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {

    View view = null;

    if(getItemViewType(position) == HEADER){
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header,parent,false);
        return new headerViewHolder(view);
    }else{
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new itemViewHolder(view);
    }

}
```
A continuacion pintamos nuestros elementos dependiendo de lo que getItemViewType nos traiga:
```java
@Override
public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    if(holder.getItemViewType() == HEADER){
        headerViewHolder itemHolder = (headerViewHolder) holder;
    }else{
        itemViewHolder itemHolder = (itemViewHolder) holder;
        itemHolder.textView.setText(lstData[position - 1]);
    }

}
```
Una vez creado nuestro adaptador ahora solo queda utilizar nuestro recycler para pintarlo en nuestra actividad:
###MainActivity
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerView = (RecyclerView) findViewById(R.id.my_list);

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    adapterList apdater = new adapterList(lstStringsNumber);
    recyclerView.swapAdapter(apdater,true);

}
```
Y al final tendremos nuestra lista creada con nuestros elementos y nuestro header dentro de ella:
![Gif](http://i.makeagif.com/media/7-20-2016/FSqQrp.gif)
