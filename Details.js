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




export class Details extends Component{
    static navigationOptions = {
        //title: 'Home',
        header:null,
    };

    async findByTitle(name) {
        let response = await AsyncStorage.getItem('@MyStore:key');
        console.log("findByTitle-details!!");
        let books = JSON.parse(response);
        var count = books.length;
        for(var i = 0; i < count ; i++) {
            if(books[i].book.title === name) {
                return i;
            }
        }
        return -1;
    }

    async updateBookDescription(index,description) {
        let response = await AsyncStorage.getItem('@MyStore:key');
        let books =  JSON.parse(response);
        console.log("book initial description:",books[index].book.description);
        console.log("new description:",description);
        books[index].book.description = description;
        //this.setState({newbooks: books});
        AsyncStorage.setItem('@MyStore:key', JSON.stringify(books));
    }

     async updateBookAuthor(index,author) {
        let response = await  AsyncStorage.getItem('@MyStore:key');
        let books =  JSON.parse(response);
         console.log("book initial description:",books[index].book.author);
         console.log("new description:",author);
        books[index].book.author = author;
        //console.log("books:",books);
        //this.setState({newbooks: books});
        AsyncStorage.setItem('@MyStore:key', JSON.stringify(books));
    }






    render() {
        const {navigate} = this.props.navigation;
        const {params} = this.props.navigation.state;
        const {goBack} = this.props.navigation;
        var book = params ? params.book : "<undefined>";



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
                    async () => {
                        var id = await this.findByTitle(params.book.title);
                        if (this.state.newDescription) {
                            await this.updateBookDescription(id, this.state.newDescription);
                        }
                        if (this.state.newAuthor) {
                            await this.updateBookAuthor(id, this.state.newAuthor);
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
