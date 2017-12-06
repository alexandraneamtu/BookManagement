/**
 * Created by alexandraneamtu on 05/12/2017.
 */

import React, { Component } from 'react';
import {
    Text,
    View,
    TouchableOpacity,
    TextInput,
    Linking,
    StyleSheet
} from 'react-native';


export class PrepareBook extends Component{
    render() {
        return (
            <View>
                <Text> Title: </Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray'}}
                    onChangeText={(title) => this.setState({title})}
                />
                <Text style={{ marginTop:28}}> Name: </Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', marginBottom:8}}
                    onChangeText={(name) => this.setState({name})}
                />
                <TouchableOpacity style={styles.reserveButton}>
                    <Text style={styles.reserveButtonText}
                          onPress={() => {
                              receiver = "test@gmail.com";
                              subject = "Reserve a book";
                              body = "Title: " + this.state.title + "\n" +
                                  "  Name: " + this.state.name;
                              all = "mailto:" + receiver + "?subject=" + subject + "&body=" + body;
                              Linking.openURL(all)}}> Reserve </Text>
                </TouchableOpacity>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    reserveButton: {
        backgroundColor: '#E91E63',
        //borderRadius: 30,
        borderColor: '#ccc',
        alignItems: 'center',
        justifyContent: 'center',
        marginBottom:45
    },
    reserveButtonText: {
        color:'#fff',
        fontSize:24,
    }
});
