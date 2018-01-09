/**
 * Created by alexandraneamtu on 05/12/2017.
 */
import {
    FlatList,
    StyleSheet,
    Text,
    View,
    TouchableOpacity,
    ScrollView,
    Image,
    RefreshControl,
    AsyncStorage,
    Alert,
    Button, ActivityIndicator
} from 'react-native';
import React, { Component } from 'react';


import {Book} from './Books'
//import SQLite from 'react-native-sqlite-storage';

const firebase = require('firebase');


const firebaseConfig = {
    apiKey: "AIzaSyANN0hiHwZd-KJC7oqSsMlXsa8Mgwq6vJI",
    authDomain: "awesomeproject-fdd85.firebaseapp.com",
    databaseURL: "https://awesomeproject-fdd85.firebaseio.com",
    projectId: "awesomeproject-fdd85",
    storageBucket: "",
    persistence:true
};
export const firebaseApp = firebase.initializeApp(firebaseConfig);


var books=[
    {book:
       new Book(
        'Gone with the wind',
        'Suzanne Collins',
        "The nation of Panem, formed from a post-apocalyptic North America, is a country that consists of a wealthy Capitol region surrounded by 12 poorer districts. Early in its history, a rebellion led by a 13th district against the Capitol resulted in its destruction and the creation of an annual televised event known as the Hunger Games. In punishment, and as a reminder of the power and grace of the Capitol, each district must yield one boy and one girl between the ages of 12 and 18 through a lottery system to participate in the games. The 'tributes' are chosen during the annual Reaping and are forced to fight to the death, leaving only one survivor to claim victory." +
        "\n" +
        "When 16-year-old Katniss's young sister, Prim, is selected as District 12's female representative, Katniss volunteers to take her place. She and her male counterpart Peeta, are pitted against bigger, stronger representatives, some of whom have trained for this their whole lives. , she sees it as a death sentence. But Katniss has been close to death before. For her, survival is second nature.",
           require('./img/book_ex.png'))},
    {book:
        new Book(
        'Pride and Prejudice',
        'Jane Austen',
        'It is a truth universally acknowledged, that a single man in possession of a good fortune must be in want of a wife.” So begins Pride and Prejudice, Jane Austen’s witty comedy of manners—one of the most popular novels of all time—that features splendidly civilized sparring between the proud Mr. Darcy and the prejudiced Elizabeth Bennet as they play out their spirited courtship in a series of eighteenth-century drawing-room intrigues. Renowned literary critic and historian George Saintsbury in 1894 declared it the “most perfect, the most characteristic, the most eminently quintessential of its author’s works,” and Eudora Welty in the twentieth century described it as “irresistible and as nearly flawless as any fiction could be.',
        require('./img/book_ex.png'))},
    {book:
        new Book(
        'The Hunger Games',
        'Margaret Mitchell',
        "Gone with the Wind is a novel written by Margaret Mitchell, first published in 1936. The story is set in Clayton County, Georgia, and Atlanta during the American Civil War and Reconstruction era. It depicts the struggles of young Scarlett O'Hara, the spoiled daughter of a well-to-do plantation owner, who must use every means at her disposal to claw her way out of the poverty she finds herself in after Sherman's March to the Sea. A historical novel, the story is a Bildungsroman or coming-of-age story, with the title taken from a poem written by Ernest Dowson." +
        "\n" +
        "Gone with the Wind was popular with American readers from the onset and was the top American fiction bestseller in the year it was published and in 1937. As of 2014, a Harris poll found it to be the second favorite book of American readers, just behind the Bible. More than 30 million copies have been printed worldwide.",
        require('./img/book_ex.png'))}
];


//books.push(new Book('title','author','description',require('./img/book_ex.png')));

/*
var books=[
    new Book(
        'Gone with the wind',
        'Suzanne Collins',
        "description1"
    ),
    new Book(
        'Pride and Prejudice',
        'Jane Austen',
        'description2'
    ),
    new Book(
        'The Hunger Games',
        'Margaret Mitchell',
        "description3"
    )
];
*/


export class BookList extends Component {
    static navigationOptions = {
        //title: 'Home',
        header:null,
    };
    constructor(props) {
        super(props);
        this._onRefresh = this._onRefresh.bind(this);
        this.state = {
            refreshing: false,
            newbooks: [],
            loading: true,
            //username: ""
        };
        console.log("dsadasdasdas");
        this.items = this.getRef().child('books');
        this.currentuser = firebase.auth().currentUser.uid;
        console.log("-------------" + this.currentuser);
        //his.username = "";
        //this.items.push(books[0]);
        //this.items.push(books[1]);
        //this.items.push(books[2]);
        //this.dosmt = this.dosmt.bind(this);


    }

    getRef() {
        return firebaseApp.database().ref();

    }



     _onRefresh() {
        this.setState({refreshing:true});
        this.setState({refreshing:false});
        this.getItems(this.items);
    }


    componentWillMount(){
        this.getItems(this.items);

    }

    deleteBook(key){
        this.items.child(key).remove();
    }

     getItems(items){
         items.on('value', (snap) => {

             // get children as an array
             var items = [];
             snap.forEach((child) => {
                 items.push({
                     book: child.val().book,
                     key: child.key
                 });
             });
             //console.log("saywhaaaaa");
             console.log(items);
             this.setState({
                 newbooks: items
             });
             this.setState({loading: false});

         });
    }

    delete(key,username){
        if(username === "admin")
        {
            Alert.alert('INFO','Are you sure you want to delete this item?',
                [
                    {text: 'Yes',
                        onPress: () => {
                            this.items.child(key).remove();
                            this._onRefresh();
                        }},
                    {text: 'No',
                        onPress: () => console.log("NOOO!!!!")}
                ],
                {cancelable: false}
            )}

        if(username === "user")
        {
            console.log(username);
            Alert.alert('','Only admins can delete books',
                [
                    {text: 'OK',
                        onPress: () => console.log("NOOO!!!!")}
                ],
                {cancelable: false}
            )
        }
    }

    showAlert(title,key){
        this.getRef().child("users").child(this.currentuser).once('value').then( snapshot =>{
            var username = (snapshot.val());
            console.log("-----------" + username);
            this.delete(key,username);

        });

    }

    render(){
        const {navigate} = this.props.navigation;
        //console.log("-----",this.state.newbooks);
        if(this.state.loading !== true) {
            //console.log("booooks",books[0]);
            //console.log("#####", this.state.newbooks[0]);
            return (

                <View style={styles.container}>
                    <View style={styles.header}>
                        <Text style={styles.headerText}>- List of Books -</Text>
                    </View>

                    <FlatList
                        refreshControl={
                            <RefreshControl
                                refreshing={this.state.refreshing}
                                onRefresh={this._onRefresh.bind(this)}
                            />
                        }
                        data = { this.state.newbooks }

                        renderItem={

                            ({item}) =>
                                <ScrollView>
                                    <View style={styles.linearView}>
                                        <Image style={{height: 70, width: 50, resizeMode: 'contain'}}
                                               source={item.book.image}/>
                                        <Text style={styles.item} onPress={
                                            () => navigate('Details', {book: item.book, key:item.key, refresh: this._onRefresh})}>{item.book.title}</Text>
                                        <View style={styles.deleteView}>
                                            <TouchableOpacity style={styles.deleteButton}
                                                    onPress={ () => { this.showAlert(item.book.title,item.key);
                                                    console.log("########", item.key)}}>
                                                <Text style={styles.reserveButtonText}> X </Text>
                                            </TouchableOpacity>
                                        </View>
                                    </View>
                                </ScrollView>
                        }
                        extraData = {this.state.newbooks}
                    />
                    <View style={styles.footer}>
                        <TouchableOpacity style={styles.reserveButton} onPress={() => {
                            this.getRef().child("users").child(this.currentuser).once('value').then(snapshot => {
                                var username = (snapshot.val());
                                console.log("-----------" + username);
                                if (username === "user") {
                                    navigate('AddBook', {refresh: this._onRefresh})
                                }

                                if (username === "admin") {
                                    console.log(username);
                                    Alert.alert('', 'Unavailable in admin mode!',
                                        [
                                            {
                                                text: 'OK',
                                                onPress: () => console.log("NOOO!!!!")
                                            }
                                        ],
                                        {cancelable: false}
                                    )
                                }
                            });

                        }
                        }>
                            <Text style={styles.reserveButtonText}> Prepare a book </Text>
                        </TouchableOpacity>
                        <TouchableOpacity style={styles.addButton} onPress={() => {
                            this.getRef().child("users").child(this.currentuser).once('value').then(snapshot => {
                                var username = (snapshot.val());
                                console.log("-----------" + username);
                                if (username === "admin") {
                                    navigate('AddBook', {refresh: this._onRefresh})
                                }

                                if (username === "user") {
                                    console.log(username);
                                    Alert.alert('', 'Only admins can add books',
                                        [
                                            {
                                                text: 'OK',
                                                onPress: () => console.log("NOOO!!!!")
                                            }
                                        ],
                                        {cancelable: false}
                                    )
                                }
                            });

                        }
                        }
                            >
                            <Text style={styles.reserveButtonText}> Add book </Text>
                        </TouchableOpacity>
                    </View>
                    <View>
                        <TouchableOpacity style={styles.signOutButton} onPress={() =>
                            navigate('Home')}>
                            <Text style={styles.reserveButtonText}> Sign out </Text>
                        </TouchableOpacity>
                    </View>

                </View>
            );
        }
        else{
            return(
                <View>
                    <View style={styles.header}>
                        <Text style={styles.headerText}>- List of Books -</Text>
                    </View>
                    <ActivityIndicator size="large" color="#E91E63" />
                </View>
            )
        }
    }
}


const styles = StyleSheet.create({
    container: {
        //flex: 1,
        height:650
        //paddingTop: 22
    },
    header:{
        backgroundColor: '#E91E63',
        alignItems: 'center',
        justifyContent: 'center',
        borderBottomWidth: 10,
        borderBottomColor: '#ddd',
        //marginTop:-40,

    },
    item: {
        padding: 10,
        fontSize: 18,
        height: 44,
    },
    headerText:{
        color: 'white',
        fontSize: 18,
        padding: 26,
    },
    footer: {
        //position: 'absolute',
        alignItems: 'center',
        //marginBottom:-30,
        top:20,
        flexDirection:'row',
        left: 0,
        right: 0,
    },
    reserveButton: {
        backgroundColor: '#E91E63',
        //borderRadius: 30,
        borderColor: '#ccc',
        //alignItems: 'center',
        justifyContent: 'center',
        marginLeft:50,
        marginBottom:45,
        marginRight:7
    },
    deleteButton: {
        backgroundColor: '#E91E63',
        //alignSelf:'flex-end',
        borderRadius: 30,
        borderColor: '#ccc',
        //alignItems: 'center',
        justifyContent: 'center',
        marginLeft:50,
        marginBottom:45,
        marginRight:7
    },

    signOutButton:{
        backgroundColor: '#E91E63',
        //borderRadius: 30,
        borderColor: '#ccc',
        alignItems: 'center',
        justifyContent: 'center',
        marginBottom:45,
        marginLeft:7,
        marginRight:7
    },
    deleteView:{
        flex: 1, flexDirection: 'row', justifyContent: 'flex-end'
    },
    addButton:{
        backgroundColor: '#E91E63',
        //borderRadius: 30,
        borderColor: '#ccc',
        //alignItems: 'center',
        justifyContent: 'center',
        marginRight:50,
        marginRight:7,
        marginBottom:45
    },
    reserveButtonText: {
        color:'#fff',
        fontSize:24,
    },
    linearView: {
        flexDirection:'row',
        padding:8,

    },
    bookTitle:{
        color:'#E91E63',
        fontSize:25,
        textAlign:'center',
    },
    detailedImage: {
        height:220,
        width: 200,
        resizeMode: 'contain',
        marginBottom:28,
        marginTop:28
    }


});