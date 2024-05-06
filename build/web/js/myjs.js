/* global CKEDITOR */

// Like wale k liye js idher likhenge because like ka button multi areas mai present hai
function doLike(pid , uid)
{
    console.log(pid+","+uid);
    
    const d = {
        
        uid : uid ,
        pid : pid ,
       operation : 'like'
        
        
    };
    
    $.ajax({
        
        url : "LikeServlet",
        data : d ,
        success : function(data,textStatus,jqXHR)
        {
            console.log(data);
            if(data.trim()=="liked")
            {
                let c = parseInt($('#like-counter-'+pid).html());
                c++;
                $('#like-counter-'+pid).html(c);
            }
            else if (data.trim() === "disliked") {
                let c = parseInt($('#like-counter-'+pid).html());
                c--;
                $('#like-counter-'+pid).html(c);
            }
            
        },
        error : function(jqXHR,textStatus,errorThrown)
        {
            console.log(data);
        }
        
    });
    
    
}
    // Other JavaScript code...

    // Function to delete a post
  



/*  function deletePost(postId) {
        // Send an AJAX request to the server to delete the post
        $.ajax({
            url: "PostDeletionServlet", // Replace with the appropriate URL of your backend servlet
            type: "GET",
            data: { post_id: postId },
            success: function (data) {
                // Handle success response, such as removing the deleted post from the UI
                // You can also show a success message here.
            },
            error: function () {
                // Handle error response
                // You can show an error message here if the post couldn't be deleted.
            }
        });
    }

    $(document).ready(function () {
        alert("hellp");
        // Add an event listener to the delete buttons
        $(".delete-post-btn").on('blur', function () {
            console.log("clicked delete");
        console.log("clicked") ; 
        const postId = $(this).data('post-id');
            swal({
                title: "Are you sure?",
                text: "Once deleted, you will not be able to recover this post!",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            }).then((willDelete) => {
                if (willDelete) {
                    deletePost(postId);
                }
            });
        });

        // Other JavaScript code...
    });
*/
    // Other JavaScript code...
 function deletePost(postId)
    {
        console.log("inside delete function");
                 
           
            console.log("clicked delete");
            console.log(postId);
            
            swal({
                title: "Are you sure?",
                text: "Once deleted, you will not be able to recover this post!",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            }).then((willDelete) => {
                if (willDelete) {
                    
              $.ajax({
            url: "DeletePostServlet", // Replace with the appropriate URL of your backend servlet
            type: "GET",
            data: { post_id: postId },
            success: function (data,textStatus,jqXHR) {
              console.log(data);
               if(data.trim()=="deleted")
               {
                swal("successfully deleted",{icon : "success"}).then(()=>
                {
                    location.reload();
                });
                
               }else{
                swal("oops something went wrong");
            }
            },
            error: function(jqXHR,textStatus,errorThrown){
               
                swal("Sorry Something went wrong!");
                // Handle error response
                // You can show an error message here if the post couldn't be deleted.
            }
        });
                
                }
            });
        
    }

    function EditPost(postId ,postTitle , postContent , postCode ) {
        // Fetch post details using AJAX or retrieve them from the page's data (if you already have them)
        // Replace the below variables with the actual data
        console.log("inside edit");

        // Set the modal fields with post details
        document.getElementById('postTitle').value = postTitle;
        CKEDITOR.instances.pContentEditor2.setData(postContent);
        document.getElementById('postCode').value = postCode;
        // Show the modal
        $('#editPostModal').modal('show');

        // Handle Save Changes button click
        document.getElementById('savePostChangesBtn').onclick = function () {
            // Get updated post details from modal form
            var updatedPostTitle = document.getElementById('postTitle').value;
            var updatedPostContent = document.getElementById('pContentEditor2').value;
            var updatedPostCode = document.getElementById('postCode').value;
            // Perform AJAX request to update post on the server (if needed)
            // You can use jQuery or other methods to perform the AJAX request
            // Example using jQuery:
            $.ajax({
                type: "POST",
                url: "UpdatePostServlet",  
                data: {
                    postId: postId,
                    postTitle: updatedPostTitle,
                    postContent: updatedPostContent,
                    postCode : updatedPostCode
                    // Add other fields as needed for updating
                },
                success: function (response) {
                   
                   if(response.trim()=="success")
                   {
                   swal("Post Updated Successfully ",{icon : "success"}).then(()=> { location.reload();});
                   
                   }
                   else
                       swal("Something went Wrong !",{icon:"error"} );
                    },
                error: function () {
                    // Handle error if the update request fails
                    swal('Error updating post. Please try again.',{icon:"error"} );
                }
            });

            // Close the modal after handling the Save Changes button click
            $('#editPostModal').modal('hide');
        };
    }

