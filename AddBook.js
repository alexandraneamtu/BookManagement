/**
 * Created by alexandraneamtu on 08/12/2017.
 */
import {
    StyleSheet,
    Text,
    View,
    TouchableOpacity,
    ScrollView,
    Image,
    RefreshControl,
    AsyncStorage,
    TextInput
} from 'react-native';
import React, { Component } from 'react';
import {Book} from './Books'

export class AddBook extends Component{

    static navigationOptions = {
        //title: 'Home',
        header:null,
    };

    print(){
        console.log(this.state.newTitle);
        console.log(this.state.newAuthor);
        console.log(this.state.newDescription);
    }

    async save()
    {
        let response = await AsyncStorage.getItem('@MyStore:key');
        let books =  JSON.parse(response);
        console.log("size:",books.length);
        console.log("book initial description:",books[books.length-1].key);
        //book = new Book(this.state.newTitle,this.state.newAuthor,this.state.newDescription,require('./img/book_1984.jpg'));
        //console.log("Save"+book);
        var count = books.length;
        books.push({key:books[count-1].key+1,book :new Book(this.state.newTitle,this.state.newAuthor,this.state.newDescription,require('./img/book_ex.png'))});
        console.log("new books"+books);
        //var count = books.length;
        //for(var i = 0; i < count ; i++) {
            //console.log(books[i].book)
        //}
        AsyncStorage.setItem('@MyStore:key', JSON.stringify(books));
    }

    render(){
        const {params} = this.props.navigation.state;
        const {goBack} = this.props.navigation;
        return(

            <View>
                <ScrollView>
                    <View style={{
                        justifyContent: 'center',
                        alignItems: 'center',
                    }}>
                        <Text style = {styles.bookTitle}>- Add a book -</Text>
                        <TextInput style= {{alignSelf: 'stretch'}} placeholder="  Title" onChangeText={(text) => this.setState({newTitle: text})}/>
                        <TextInput style= {{alignSelf: 'stretch'}} placeholder="  Author" onChangeText={(text) => this.setState({newAuthor: text})}/>
                        <TextInput style={{alignSelf: 'stretch'}} placeholder ="  Description" multiline={true} onChangeText={(text) => this.setState({newDescription: text})} />

                    </View>
                </ScrollView>
                <View>
                    <TouchableOpacity style={styles.reserveButton}
                                      onPress={
                                          async () =>{
                                              await this.save();
                                              params.refresh();
                                              goBack();
                                          }
                                      }
                        //async () => {
                            //var id = await this.findByTitle(params.book.title);
                            //if (this.state.newDescription) {
                                //await this.updateBookDescription(id, this.state.newDescription);
                            //}
                            //if (this.state.newAuthor) {
                                //await this.updateBookAuthor(id, this.state.newAuthor);
                            //}
                            //params.refresh();
                            //goBack();
                        //}
                    //}
                    >
                        <Text style={styles.reserveButtonText}>Save</Text>
                    </TouchableOpacity>
                </View>
            </View>
        )
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
