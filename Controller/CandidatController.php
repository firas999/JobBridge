<?php

namespace App\Controller;

use App\Entity\Candidat;
use App\Entity\Urlizer;
use App\Entity\User;
use App\Form\CandidatType;
use App\Repository\CandidatRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\EncoderFactoryInterface;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Exception\ExceptionInterface;

use Dompdf\Dompdf;
use Dompdf\Options;

/**
 * @Route("/admin")
 */
class CandidatController extends AbstractController
{
    private EncoderFactoryInterface $encoder;
    private UserPasswordEncoderInterface $pwdEncoder;
    public function __construct(EncoderFactoryInterface $encoder,UserPasswordEncoderInterface $enc)
    {
        $this->encoder = $encoder;
        $this->pwdEncoder = $enc;
    }
    /**
     * @Route("/indexcandidat", name="candidat_index", methods={"GET"})
     */
    public function index(CandidatRepository $candidatRepository): Response
    {
        return $this->render('candidat/index.html.twig', [
            'candidats' => $candidatRepository->findAll(),
        ]);
    }



    /**
     * @Route("/newcandidat", name="candidat_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {


        $candidat = new Candidat();
        $salt = md5(microtime());
        $form = $this->createForm(CandidatType::class, $candidat);
        $form->handleRequest($request);
        $encoder = $this->encoder->getEncoder(User::class);
        if ($form->isSubmitted() && $form->isValid())
        { /** @var UploadedFile $uploadedFile */
            $uploadedFile = $form['imageFile']->getData();
            $destination = $this->getParameter('kernel.project_dir').'/public/uploads';
            $originalFilename = pathinfo($uploadedFile->getClientOriginalName(), PATHINFO_FILENAME);
            $newFilename = Urlizer::urlize($originalFilename).'-'.uniqid().'.'.$uploadedFile->guessExtension();
            $uploadedFile->move(
                $destination,
                $newFilename
            );
            $candidat->setImg($newFilename);
            $entityManager = $this->getDoctrine()->getManager();
            $encodedPassword=$encoder->encodePassword($candidat->getMotdepasse(),$salt);
            $candidat->setMotdepasse($this->pwdEncoder->encodePassword($candidat,$candidat->getMotdepasse()));
            $candidat->setRoles(['ROLE_CANDIDATE']);

            $entityManager->persist($candidat);
            $entityManager->flush();

            return $this->redirectToRoute('candidat_index');}


        return $this->render('candidat/new.html.twig', [
            'candidat' => $candidat,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}/showcandidat", name="candidat_show", methods={"GET"})
     */
    public function show(Candidat $candidat): Response
    {
        return $this->render('candidat/show.html.twig', [
            'candidat' => $candidat,
        ]);
    }


    /**
     * @Route("/{id}/editcandidat", name="candidat_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Candidat $candidat): Response
    {
        $salt = md5(microtime());
        $form = $this->createForm(CandidatType::class, $candidat);

        $form->handleRequest($request);
        $encoder = $this->encoder->getEncoder(User::class);

        if ($form->isSubmitted() && $form->isValid()) {
            /** @var UploadedFile $uploadedFile */
            $uploadedFile = $form['imageFile']->getData();
            $destination = $this->getParameter('kernel.project_dir').'/public/uploads';
            $originalFilename = pathinfo($uploadedFile->getClientOriginalName(), PATHINFO_FILENAME);
            $newFilename = Urlizer::urlize($originalFilename).'-'.uniqid().'.'.$uploadedFile->guessExtension();
            $uploadedFile->move(
                $destination,
                $newFilename
            );$candidat->setImg($newFilename);
            $this->getDoctrine()->getManager()->flush();
            $encodedPassword=$encoder->encodePassword($candidat->getMotdepasse(),$salt);
            $candidat->setMotdepasse($this->pwdEncoder->encodePassword($candidat,$candidat->getMotdepasse()));
            $candidat->setRoles(['ROLE_CANDIDATE']);


            return $this->redirectToRoute('candidat_index');
        }

        return $this->render('candidat/edit.html.twig', [
            'candidat' => $candidat,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="candidat_delete", methods={"DELETE"})
     */
    public function delete(Request $request, Candidat $candidat): Response
    {
        if ($this->isCsrfTokenValid('delete'.$candidat->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($candidat);
            $entityManager->flush();
        }

        return $this->redirectToRoute('candidat_index');
    }
    /**
     * @Route("/recherche", name="rechercheCandidat")
     */
    public function searchAction(Request $request)
    {

        $data = $request->request->get('search');


        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery(
            'SELECT e FROM App\Entity\Candidat e
    WHERE e.secteur    LIKE :data')
            ->setParameter('data', '%'.$data.'%');


        $candidats = $query->getResult();

        return $this->render('candidat/index.html.twig', array(
            'candidats' => $candidats));

    }
    /**
     * @Route("/tri", name="triCandidat")
     */
    public function TriAction(Request $request)
    {




        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery(
            'SELECT e FROM App\Entity\Candidat e
    ORDER BY e.tailleentreprise ASC');



        $candidats = $query->getResult();

        return $this->render('candidat/index.html.twig', array(
            'candidats' => $candidats));

    }


    /**
     * @Route("/trinom", name="triNom")
     */
    public function TriNom(Request $request)
    {




        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery(
            'SELECT e FROM App\Entity\Candidat e
    ORDER BY e.nom ASC');



        $candidats = $query->getResult();

        return $this->render('candidat/index.html.twig', array(
            'candidats' => $candidats));

    }

    /**
     * @Route("/searchcandidatx ", name="searchcandidatx", methods={"GET"})
     */
    public function searchCandidatx(Request $request,NormalizerInterface $Normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Candidat::class);
        $requestString=$request->get('searchValue');
        $candidats = $repository->findCandidatBy($requestString);
        $jsonContent = $Normalizer->normalize($candidats, 'json',['groups'=>'candidat']);
        $retour=json_encode($jsonContent);
        return new Response($retour);

    }
    /**
     * @Route("/cv/{id}", name="candidat_cvFront", methods={"GET"})
     */
    public function showCV(Candidat $candidat): Response
    {
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);


        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('candidat/cv.html.twig', [
            'candidat' => $candidat,
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (inline view)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => false
        ]);

    }

}

