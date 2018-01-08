/**
 * Created by alexandraneamtu on 05/12/2017.
 */
import React, { Component } from 'react';
import {
    StyleSheet,
    Text,
    View,
    ScrollView,
    Image,
    TextInput,
    Button,
    AsyncStorage,
    TouchableOpacity,
    processColor,

} from 'react-native';
import {StackNavigator, SafeAreaView} from 'react-navigation';
import {firebaseApp} from './BookList'



export class Details extends Component{
    static navigationOptions = {
        //title: 'Home',
        header:null,
    };




     updateBookDescription(index,description) {
        const items = firebaseApp.database().ref().child('books');

        console.log("keeeeeeeeeeeeeeyyyyyyyyyy",index);
        items.child(index).child("book").child("description").set(description);
    }

     updateBookAuthor(index,author) {
         const items = firebaseApp.database().ref().child('books');

         console.log("keeeeeeeeeyyyyyyyyyyyyy:",index);
         items.child(index).child("book").child("author").set(author);
    }






    render() {
        const {navigate} = this.props.navigation;
        const {params} = this.props.navigation.state;
        const {goBack} = this.props.navigation;
        var book = params ? params.book : "<undefined>";
        var key = params ? params.key : "<undefined>"


        return (
            <View>
                <ScrollView>
                    <View style={{
                    justifyContent: 'center',
                    alignItems: 'center',
                }}>
                        <Text style = {styles.bookTitle}>- {book.title} -</Text>
                        <TextInput style = {{width:150,textAlign:'center'}} onChangeText={(text) => this.setState({newAuthor: text})}> {book.author} </TextInput>
                        <Image style={styles.detailedImage} source={book.image}/>
                        <ScrollView>
                            <TextInput style={{height:160, width: 350, marginTop:10 }} multiline={true} onChangeText={(text) => this.setState({newDescription: text})}> {book.description} </TextInput>
                        </ScrollView>
                    </View>
                </ScrollView>
                <View>
                    <TouchableOpacity style={styles.reserveButton} onPress={
                     () => {
                         console.log("###########SADSADASDASDASDA###################");
                        if (this.state.newDescription) {
                             this.updateBookDescription(key, this.state.newDescription);
                        }
                        if (this.state.newAuthor) {
                                this.updateBookAuthor(key, this.state.newAuthor);
                        }
                        params.refresh();
                        goBack();
                    }
                }>
                        <Text style={styles.reserveButtonText}>Save changes </Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.reserveButton} onPress={() =>
                        navigate('Chart',{title:book.title})}>
                        <Text style={styles.reserveButtonText}> Chart </Text>
                    </TouchableOpacity>
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
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
        position: 'absolute',
        alignItems: 'center',
        bottom: 0,
        left: 0,
        right: 0,
    },
    reserveButton: {
        backgroundColor: '#E91E63',
        //borderRadius: 30,
        borderColor: '#ccc',
        alignItems: 'center',
        justifyContent: 'center',
        marginBottom:45,

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
